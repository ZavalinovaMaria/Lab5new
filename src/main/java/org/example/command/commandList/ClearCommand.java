package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * удаления всех элементов коллекции.
 */
public class ClearCommand implements Command {

    GroupsCollectionManager collection;

    public ClearCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Метод выполняет очистку коллекции и хранилища идентификаторов, после чего обновляет данные коллекции
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        collection.getCollection().clear();
        collection.getIdStorage().clear();
        collection.updateData();
        System.out.println(successExecutionMessage());
    }

    @Override
    public String description() {
        return "clear: очистить коллекцию";
    }

    @Override
    public String successExecutionMessage() {
        return "Коллекция пуста";
    }

}

