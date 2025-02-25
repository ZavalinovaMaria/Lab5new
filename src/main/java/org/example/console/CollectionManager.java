package org.example.console;

import org.example.Command.Command;
import org.example.exceptions.NotExistingValueException;
import org.example.fileWork.FileManager;
import org.example.subjects.StudyGroup;
import org.example.subjects.comporators.ComparatorStudentsCount;

import java.io.File;
import java.time.DateTimeException;
import java.util.*;

import static org.example.console.Console.firstFilePath;
import static org.example.console.Console.idStoragee;
import static org.example.console.Invoker.commands;


public class CollectionManager {
    private GroupsCollection collection;
    private final FileManager fileManager;
    private Set<String> scriptHistory = new HashSet<>();
    private final Creator creator;


    private String[] compositeCommand = new String[9];

    private boolean isScriptWorking = false;
    private String[] tokens;

    public CollectionManager(String path) {
        this.fileManager = new FileManager();
        this.creator = new Creator();
        this.collection = new GroupsCollection(fileManager.read(path));
    }


    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public void clearCompositeCommand() {
        compositeCommand = new String[9];
    }

    public void setCompositeCommand(String[] compositeCommand) {
        this.compositeCommand = compositeCommand;
    }

    public void info() {
        System.out.print("Collection information:");
        System.out.println(collection.toString());
    }

    public void help() {
        for (Command com : commands.values()) {
            System.out.println(com.description());
        }
    }

    public void show() {
        System.out.println("The collection: ");
        System.out.println(collection.getCollection());
    }

    public void save() {
        String filePath = firstFilePath;
        fileManager.write(collection.getCollection(), filePath);
        }

    public void exit() {
        System.out.println("Session ended");
        System.out.println("Конец текущей сессии");
        System.exit(0);
    }

    public void clear() {
        collection.getCollection().clear();
        collection.updateData();
        System.out.println("Коллекция пуста");
    }

    //remove_by_id id : удалить элемент из коллекции по его id
    public void removeById() {
        try {
            int id;
            if (isScriptWorking) {
                id = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                id = Integer.parseInt(tokens[1]);
            }
            if (collection.deleteId(id)) {
                collection.getCollection().remove(collection.findIndexById(id));
                collection.updateData();
                System.out.println("Элемент успешно удален ");
            } else System.out.println("Не удалось удалить элемент ");


        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }

    }

    public void add() {
        if (isScriptWorking) {
            try {
                StudyGroup newGroup = creator.toBuildGroup(compositeCommand);
                if (collection.addNewId(newGroup.getId())) {
                    collection.getCollection().add(newGroup);

                    clearCompositeCommand();
                    System.out.println("Новый объект добавлен в коллекцию");
                }
            } catch (NumberFormatException E) {
                System.out.println("Ошибка заполнения полей ");
            }catch (NullPointerException | IllegalArgumentException |DateTimeException e) {
                System.out.println("Не удалось добавить элемент из-за неккоректных данных");}

        } else {
            StudyGroup newGroup = creator.createGroup();
            collection.getCollection().add(newGroup);
            idStoragee.add(newGroup.getId());
            System.out.println("Новый объект добавлен в коллекцию");
        }
        collection.updateData();
    }

    public void updateId() {
        try {
            int id;
            if (isScriptWorking) {
                id = Integer.parseInt(compositeCommand[0]);
                if (collection.checkingExistence(id)) {
                     creator.toBuildUpdationGroup(collection.getCollection().get(collection.findIndexById(id)), compositeCommand);
                    clearCompositeCommand();
                    System.out.println("Значение элемента успешно обновлено");
                }
            } else {
                id = Integer.parseInt(tokens[1]);
                if (collection.checkingExistence(id)) {
                    creator.toUpdateGroup(collection.getCollection().get(collection.findIndexById(id)));
                    System.out.println("Значение элемента успешно обновлено");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
            System.out.println("Не удалось обновить  элемент из-за неккоректных данных");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным  не id обнаружен ");
        }
    }

    public void executeScript() {
        try {
            File script;
            if (isScriptWorking) {
                script = new File(compositeCommand[0]);
            } else {
                script = new File(tokens[1]);
            }
            if (!script.exists()) {
                System.out.println("Указанный файл не существует");
                return;
            }
            if (scriptHistory.contains(compositeCommand[0])) {
                System.out.println("Данный скрипт уже был выполнен");
                return;
            }
            if (isScriptWorking) {
                scriptHistory.add(compositeCommand[0]);
            } else {
                scriptHistory.add(tokens[1]);
            }

            isScriptWorking = true;
            ScriptManager scriptManager = new ScriptManager(script, this);
            //System.out.println("Выполнение скрипта");
            if (isScriptWorking) {
                clearCompositeCommand();
            }
            scriptManager.executeScript();
            isScriptWorking = false;
            if(!scriptHistory.contains(compositeCommand[0])){
            System.out.println("Выполнение скрипта завершилось успешно");}

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пожалуйста, введите имя файла");
        }
    }



    public void removeAt() {
        try {
            int index;
            if (isScriptWorking) {
                index = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                index = Integer.parseInt(tokens[1]);
            }
            try {
                collection.deleteId(collection.getCollection().get(index).getId());
                collection.getCollection().remove(index);
                collection.updateData();
                System.out.println("Элемент успешно удален ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Элемента с таким индексом нет в коллекции ");

            }} catch (NumberFormatException e) {
                System.out.println("Id должен быть числом ");
            }
        }

    public void removeLower(){
        try {
            int id;
            if (isScriptWorking) {
                id = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                 id= Integer.parseInt(tokens[1]);
            }
            if (collection.checkingExistence(id)) {
                Iterator<StudyGroup> iterator = collection.getCollection().iterator();
                while (iterator.hasNext()) {
                    StudyGroup group = iterator.next();
                    if (group.getStudentsCount() < collection.getCollection().get(collection.findIndexById(id)).getStudentsCount()) {
                        iterator.remove();
                        collection.deleteId(group.getId());
                        System.out.println("Элемет c id "+group.getId()+ " успешно удален ");
                    }
                }
                collection.updateData();
            }else{
                System.out.println("Коллекция осталась без изменений ");
            }

        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с введенным id не содержится в коллекции, ");
        }
    }
    public void reorder(){
        if(collection.getCountOfElements()>1){
        Collections.reverse(collection.getCollection());
        System.out.println("Коллекция отсортирована,для просмотра изменений наберите show");}
        else{
            System.out.println("Недостаточно элементов для сортировки ");
    }}

    //вывести среднее значение поля studentsCount для всех элементов коллекции
    public void averageOfStudentsCount(){
        int averageCount=0;
        for(StudyGroup group :collection.getCollection()){
            averageCount+=group.getStudentsCount();
        }
        System.out.println("Среднее значение поля studentsCount: "+averageCount/collection.getCountOfElements());
    }

    //filter_by_group_admin groupAdmin : вывести элементы, значение поля groupAdmin которых равно заданному
    public void filterAdmin(){
        String input;
        if (isScriptWorking) {
            input = compositeCommand[0];
            clearCompositeCommand();
        } else {
            input = tokens[1];
        }
        ArrayList<StudyGroup> elements = new ArrayList<>();
        for (StudyGroup group : collection.getCollection()) {
            if (group.getGroupAdmin() != null) {
                String value = group.getGroupAdmin().getName();
                if (value.equals(input)) {
                    elements.add(group);
                }
            }
        }

        if (elements.isEmpty()) {
            System.out.println("В коллекции нет элементов, содержащих такое имя старосты ");
        } else {
            System.out.println("Данное имя старосты имеют следущие элементы:");
            for (StudyGroup element : elements) {
                System.out.println(element);
            }
        }
    }
    //print_field_descending_semester_enum : вывести значения поля semesterEnum всех элементов в порядке убывания
    public void printFieldDescendingSemestr(){

        List<StudyGroup> sortable = new ArrayList<>(collection.getCollection());
        Comparator<StudyGroup> sortibility = new ComparatorStudentsCount();
        sortable.sort(sortibility);
        Collections.reverse(sortable);
        System.out.println("Вывод значения поля semesterEnum всех элементов в порядке убывания: ");
        for (StudyGroup group : sortable) {
            System.out.println("Id группы:"+group.getId()+","+group.getSemesterEnum());
        }
    }
    }




    //execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
    //remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
    //remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
    //reorder : отсортировать коллекцию в порядке, обратном нынешнему
    //average_of_students_count : вывести среднее значение поля studentsCount для всех элементов коллекции

    //print_field_descending_semester_enum : вывести значения поля semesterEnum всех элементов в порядке убывания









