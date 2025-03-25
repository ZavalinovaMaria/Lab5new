package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;

import java.util.Collections;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * изменения порядка элементов в коллекции на обратный
 */
public class ReorderCommand implements Command {
    GroupsCollectionManager collection;

    public ReorderCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду изменения порядка элементов в коллекции на обратный.
     * Если количество элементов в коллекции больше одного, порядок элементов изменяется на обратный с помощью метода
     * {@link Collections#reverse(java.util.List)} и выводится сообщение об успешном выполнении команды.
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        if (collection.getCountOfElements() > 1) {
            Collections.reverse(collection.getCollection());
            System.out.println(successExecutionMessage());
        } else {
            System.out.println("Недостаточно элементов для сортировки ");
        }
    }

    @Override
    public String description() {
        return "reorder: отсортировать коллекцию в порядке обратном нынешнему";
    }

    @Override
    public String successExecutionMessage() {
        return "Коллекция отсортирована,для просмотра изменений наберите show";
    }

}