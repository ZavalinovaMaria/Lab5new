package org.example.console;

import org.example.Command.Command;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NotUniqueValueException;
import org.example.fileWork.FileManager;
import org.example.subjects.StudyGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.console.Console.firstFilePath;
import static org.example.console.Console.idStoragee;
import static org.example.console.Invoker.commands;


public class CollectionManager {
    private GroupsCollection collection;
    private final FileManager fileManager;
    private Set<String> scriptHistory = new HashSet<>();
    private final Creator creator;

    /**
     * A field that contains history of executed commands
     */
    private final List<String> commandHistory = new ArrayList<>();


    private String[] compositeCommand = new String[9];

    private boolean isScriptWorking = false;
    /**
     * String array to which commands are sent from the console
     */
    private String[] tokens;


    public CollectionManager(String path) {
        this.fileManager = new FileManager();
        this.creator = new Creator();
        this.collection = new GroupsCollection(fileManager.read(path));
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void addToHistory(String command) {
        commandHistory.add(command);
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
        String filePath = null;
        filePath = firstFilePath;
        fileManager.saveFile(collection, filePath);}

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
                collection.getCollection().remove(id);
                collection.updateData();
                System.out.println("Элемент успешно удален ");
            } else System.out.println("Не удалось удалить элемент ");

//проверка на то что элемент с таким id воообще есть в коллекции + удалить из хранилища 
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }

    }

    public void add() {
        if (isScriptWorking) {
            try {
                StudyGroup newGroup = creator.toBuildGroup(compositeCommand);
                if (collection.checkingUniqueness(newGroup.getId())) {
                    collection.getCollection().add(newGroup);
                    idStoragee.add(newGroup.getId());

                    clearCompositeCommand();
                    System.out.println("Новый объект добавлен в коллекцию");
                }
            } catch (NumberFormatException E) {
                System.out.println("Ошибка заполнения полей ");
            } catch (NotUniqueValueException e) {
                System.out.println(e.getMessage());
            }

        } else {
            StudyGroup newGroup = creator.createGroup();
            collection.getCollection().add(newGroup);;
        }
        collection.updateData();
    }

    public void updateId() {
        try {
            int id;
            if (isScriptWorking) {
                System.out.println("1");
                id = Integer.parseInt(compositeCommand[0]);
                if (collection.checkingExistence(id)) {
                    System.out.println("2");
                    // insert.toBuildUpdationTicket(collection.getCollection().get(id), compositeCommand);
                    clearCompositeCommand();
                }
            } else {
                id = Integer.parseInt(tokens[1]);
                if (collection.checkingExistence(id)) {
                    //insert.toUpdateTicket(collection.getCollection().get(id));
                    System.out.println("Значение элемента успешно обновлено");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным ключем не обнаружен ");
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
            ScriptManager scriptManager = new ScriptManager(script, commands, this);
            System.out.println("Выполнение скрипта");
            if (isScriptWorking) {
                clearCompositeCommand();
            }
            scriptManager.executeScript();
            isScriptWorking = false;
            System.out.println("Выполнение скрипта завершилось успешно");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пожалуйста, введите имя файла");
        }
    }



    public void removeAt(){}
    public void removeLower(){}
    public void reorder(){}

    //вывести среднее значение поля studentsCount для всех элементов коллекции
    public void averageOfStudentsCount(){
        int averageCount=0;
        for(StudyGroup group :collection.getCollection()){
            averageCount+=group.getStudentsCount();
        }
        System.out.println("среднее: "+averageCount);
    }

    //filter_by_group_admin groupAdmin : вывести элементы, значение поля groupAdmin которых равно заданному
    // будем делать по имени или по обекту?---может быть одно имя и разное наполение
    //по имени сделано
    //если по обекту--хуйня какая то не понимаю
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
            String value = group.getGroupAdmin().getName();
            if (value==input) {
                elements.add(group);
            }
        }
        if (elements.isEmpty()) {
            System.out.println("В коллекции нет элементов, содержащих данную подстроку");
        } else {
            System.out.println("Данную подстроку в имени имеют следущие элементы:");
            for (StudyGroup element : elements) {
                System.out.println(element);
            }
        }
    }
    //print_field_descending_semester_enum : вывести значения поля semesterEnum всех элементов в порядке убывания
    //в порядке убывания чего ? семестра или людей
    public void printFieldDescendingSemestr(){

    }


    //add {element} : добавить новый элемент в коллекцию
    //update id {element} : обновить значение элемента коллекции, id которого равен заданному
    //execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
    //remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
    //remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
    //reorder : отсортировать коллекцию в порядке, обратном нынешнему
    //average_of_students_count : вывести среднее значение поля studentsCount для всех элементов коллекции

    //print_field_descending_semester_enum : вывести значения поля semesterEnum всех элементов в порядке убывания







    }

