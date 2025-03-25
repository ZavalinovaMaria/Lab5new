package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.console.Invoker;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * вывода справки по доступным командам.
 */
public class HelpCommand implements Command {
    GroupsCollectionManager collection;

    public HelpCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду вывода справки по доступным командам.
     * Перебираются все команды, зарегистрированные в {@link Invoker}, и выводятся их описания.</li>
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        System.out.println(successExecutionMessage());
        for (Command command : Invoker.getCommands().values()) {
            System.out.println(command.description());
        }
    }

    @Override
    public String description() {
        return "help: вывести справку по доступным командам";
    }

    @Override
    public String successExecutionMessage() {
        return "Справка по доступным командам:";
    }

}