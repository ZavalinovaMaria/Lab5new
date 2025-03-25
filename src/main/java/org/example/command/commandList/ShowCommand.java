package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * вывода всех элементов коллекции в строковом представлении
 */
public class ShowCommand implements Command {
    GroupsCollectionManager collection;

    public ShowCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду вывода всех элементов коллекции в строковом представлении.
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        if (collection.getCountOfElements() == 0) System.out.println("Коллекция пуста.");
        else {
            System.out.println(successExecutionMessage() + collection.getCollection());
        }
    }

    @Override
    public String description() {
        return "show: вывести все элементы в строковом представлении";
    }

    @Override
    public String successExecutionMessage() {
        return "Вывод элементов коллекции";
    }

}