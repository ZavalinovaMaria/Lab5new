package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * завершения сессии.
 */
public class ExitCommand implements Command {

    GroupsCollectionManager collection;

    public ExitCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * При вызове метода выводится сообщение об успешном завершении работы сессии, после чего вызывается
     * {@code System.exit(0)} для немедленного завершения работы программы.
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        System.out.println(successExecutionMessage());
        System.exit(0);
    }


    @Override
    public String description() {
        return "exit: завершить программу";
    }

    @Override
    public String successExecutionMessage() {
        return "Конец текущей сессии";
    }

}
