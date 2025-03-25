package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * вывода информации о коллекции.
 */
public class InfoCommand implements Command {
    GroupsCollectionManager collection;

    public InfoCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Метод выводит сообщение об успешном выполнении команды, возвращаемое {@link #successExecutionMessage()},
     * после чего вызывается {@code toString()} у {@code collection} для получения и вывода подробной информации.
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        System.out.print(successExecutionMessage());
        System.out.println(collection.toString());
    }

    @Override
    public String description() {
        return "info: вывести информацию о коллекции";
    }

    @Override
    public String successExecutionMessage() {
        return "Информация о коллекции:";
    }

}