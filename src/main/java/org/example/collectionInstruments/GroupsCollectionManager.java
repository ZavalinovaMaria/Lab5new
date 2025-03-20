package org.example.collectionInstruments;

import org.example.subjects.StudyGroup;
import java.util.*;
import java.util.ArrayList;

/**
 * Класс, управляющий коллекцией элементов
 */
public class GroupsCollectionManager implements CheckingId {
    private ArrayList<StudyGroup> groups;
    private Date initializationDate;
    private final String type;
    private int countOfElements;
    private final String internalFileType;
    private final ArrayList<Integer> idStorage = new ArrayList<>();
    private final String createdFromThisFilePath;

    public GroupsCollectionManager(ArrayList<StudyGroup> groups,String filePath){
        this.createdFromThisFilePath = filePath;
        type = groups.getClass().getSimpleName();
        internalFileType = "StudyGroups";
        ArrayList<StudyGroup> realGroups = new ArrayList<>();
        for(StudyGroup group:groups){
            if(addNewId(group.getId(),idStorage)){
                realGroups.add(group);
            }
            this.groups = realGroups;
            countOfElements = realGroups.size();
            initializationDate = new Date();
        }
    }


    public  ArrayList<Integer> getIdStorage() {
        return idStorage;
    }

    public String getFilePathCreatedFrom() {
        return createdFromThisFilePath;
    }

    /**
     * Обновляет информацию о размере коллекции и дате ее создания(обновления)
     */
    public void updateData() {
        countOfElements = groups.size();
        initializationDate = new Date();
    }

    /**
     * Возвращает индекс первого элемента, чей ID совпадает с переданным значением.
     * @param id ID элемента, индекс которого нужно найти.
     * @return индекс элемента с заданным ID, или -1, если элемент не найден.
     */
    public int findIndexById(int id) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Генерирует новый уникальный ID, что достигается путем выбора максимального существующего ID в
     * {@code idStorage} и увеличения его на 1.
     * @return следующий уникальный ID, который гарантированно не пересекается с уже существующими.
     */
    public int generateNextId(){
        int max = 0;
        for(int i:this.idStorage){
            if(i>max){
                max=i;
            }
        }
        return max+1;
    }

    /**
     * Добавляет новый элемент в коллекцию с уникальным идентификатором.
     * .
     * Метод генерирует новый уникальный ID с помощью {@link #generateNextId()},
     * устанавливает его для переданного объекта {@code StudyGroup},
     * добавляет элемент в коллекцию и обновляет хранилище ключей {@code idStorage}.
     * После этого вызывается {@code updateData()} для сохранения актуального состояния коллекции.
     * @param group объект {@link StudyGroup}, который будет добавлен в коллекцию
     */
    public void add(StudyGroup group) {
        int newId = generateNextId();
        group.setId(newId);
        groups.add(group);
        idStorage.add(newId);
        updateData();
    }

    /**
     * Безопасно удаляет элемент с заданным id, вызвав метод {@link #deleteId(int, ArrayList)}.
     * @param id id элемента который должен быть удален
     */
    public void deleteById(int id){
        if(deleteId(id,idStorage)){
            groups.remove(findIndexById(id));
            updateData();
        }
    }

    /**
     * Безопасно удаляет элемент с заданным индексом, вызвав метод {@link #deleteId(int, ArrayList)}.
     * @param index индекс элемента который должен быть удален
     */
    public void deleteByIndex(int index){
        if(deleteId(groups.get(index).getId(), idStorage)){
            groups.remove(index);
            updateData();
        }
    }

    public ArrayList<StudyGroup> getCollection() {
        return groups;
    }
    @Override
    public String toString() {
        return  "\n1. Initialization date: " + initializationDate +
                "\n2. Collection type: " + type +
                "\n3. Internal files type: " + internalFileType +
                "\n4. Amount of elements: " + countOfElements;
    }
    public int getCountOfElements() {
        return countOfElements;
    }



}

