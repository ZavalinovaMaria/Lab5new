package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.subjects.StudyGroup;

import java.util.*;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * вывода значений поля {@code semesterEnum} всех элементов коллекции в порядке убывания.
 */
public class PrintFieldDescendingSemestrCommand implements Command {
    GroupsCollectionManager collection;

    public PrintFieldDescendingSemestrCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду вывода значений поля {@code semesterEnum} всех элементов коллекции в порядке убывания.
     * Сначала Копирует элементы коллекции в список.
     * Затем сортирует список по значению полю {@code  StudentsCount}с помощью {@link Collections#sort(List)}
     * и для каждого элемента выводит его id и значение поля {@code semesterEnum}.</li>
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        List<StudyGroup> sortable = new ArrayList<>(collection.getCollection());
        Collections.sort(sortable);
        System.out.println(successExecutionMessage());
        for (StudyGroup group : sortable) {
            System.out.println("Id группы:" + group.getId() + "," + group.getSemesterEnum());
        }
    }

    @Override
    public String description() {
        return "print_field_descending_semestr: вывести значения поля semestrEnum всех элементов в порядке убывания";
    }

    @Override
    public String successExecutionMessage() {
        return "Вывод значения поля semesterEnum всех элементов в порядке убывания: ";
    }

}