package org.example.console;

import org.example.exceptions.NotExistingValueException;
import org.example.subjects.StudyGroup;

import java.util.*;
import java.util.ArrayList;


public class GroupsCollectionManager implements Checking {
    private ArrayList<StudyGroup> groups;
    private Date initializationDate;
    private String type;
    private int countOfElements;
    private String internalFileType;
    private  ArrayList<Integer> idStorage = new ArrayList<>();
    private final String createFromThisFilePath;

    public GroupsCollectionManager(ArrayList<StudyGroup> groups,String filePath){
        this.createFromThisFilePath = filePath;
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
    }}

    public  ArrayList<Integer> getIdStorage() {
        return idStorage;
    }

    public String getCreateFromThisFilePath() {
        return createFromThisFilePath;
    }

    public void updateData() {
        countOfElements = groups.size();
        initializationDate = new Date();
    }
    public int findIndexById(int id) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public int generateNextId(){
        int max = 0;
        for(int i:this.idStorage){
            if(i>max){
                max=i;
            }
        }
        return max+1;
    }
    public void add(StudyGroup group){
        int newId = generateNextId();
        group.setId(newId);
        getCollection().add(group);
        idStorage.add(newId);
        System.out.println("Новый объект добавлен в коллекцию");
       //Создали группу с пустым айди(в другом месте)--здесь генерируем новый(он автоматически уникален)--добавляем новый в хралилище
}//по идее из-за валидации этот метод или будет выполняться или нет-исключения уже  в классе команде

    public void deleteById(int id){
        if(deleteId(id,idStorage)){
            getCollection().remove(findIndexById(id));
            updateData();
            System.out.println("Элемент успешно удален ");
        }

    }
    public void deleteByIndex(int index){
        if(deleteId(getCollection().get(index).getId(), idStorage)){
        getCollection().remove(index);
        updateData();
        System.out.println("Элемент успешно удален ");
        }
    }

    //МЕТОД ДОБАВЛЕНИЯ НОВОГО АЙДИ МОЖНО УДАЛИТЬ ???--после тестинка
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

