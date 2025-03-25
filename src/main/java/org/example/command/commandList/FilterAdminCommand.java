package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;

import java.util.ArrayList;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * фильтрации элементов коллекции по значению имени старосты.
 */
public class FilterAdminCommand implements Command {

    GroupsCollectionManager collection;

    public FilterAdminCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду фильтрации элементов коллекции по значению имени старосты.
     * Метод получает аргумент команды (имя старосты) из {@link CommandContext#getArgument()}. Если аргумент не задан,
     * выводится сообщение об ошибке, и выполнение команды прекращается.
     * Затем происходит перебор элементов коллекции. Если имя старосты элемента совпадает с заданным,
     * элемент добавляется в результирующий список. После завершения перебора, если были найдены соответствующие
     * элементы, они выводятся в консоль; в противном случае выводится сообщение о том, что таких элементов нет.
     *
     * @param context контекст выполнения команды, содержащий аргумент (имя старосты) для фильтрации.
     */
    @Override
    public void execute(CommandContext context) {
        String admin;
        try {
            admin = context.getArgument();
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
            return;
        }
        ArrayList<StudyGroup> requiredElements = new ArrayList<>();
        for (StudyGroup group : collection.getCollection()) {
            String value = group.getGroupAdmin().getName();
            if (value.equals(admin)) {
                requiredElements.add(group);
            }
        }
        if (requiredElements.isEmpty()) {
            System.out.println("В коллекции нет элементов, содержащих такое имя старосты ");
        } else {
            System.out.println(successExecutionMessage());
            for (StudyGroup element : requiredElements) {
                System.out.println(element);
            }
        }
    }

    @Override
    public String description() {
        return "filter_admin {name}: вывести элементы, значения поля groupAdmin которых равно заданному";
    }

    @Override
    public String successExecutionMessage() {
        return "Данное имя старосты имеют следущие элементы:";
    }

}
