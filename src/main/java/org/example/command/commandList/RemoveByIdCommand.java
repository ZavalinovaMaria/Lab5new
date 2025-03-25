package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * удаления элемента коллекции по id
 */
public class RemoveByIdCommand implements Command {
    GroupsCollectionManager collection;

    public RemoveByIdCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Метод получает аргумент из {@link CommandContext#getArgument()} и пытается преобразовать его в целое число.
     * Если аргумент отсутствует, выбрасывается {@link NullValueException} с соответствующим сообщением.
     * При корректном значении происходит удаление элемента по id
     * В случае возникновения {@link NumberFormatException} выводится сообщение о том, что id
     * должен быть числом.
     *
     * @param context контекст выполнения команды, содержащий аргумент в виде строки.
     */
    @Override
    public void execute(CommandContext context) {
        int id;
        try {
            id = Integer.parseInt(context.getArgument());
            collection.deleteById(id);
            System.out.println(successExecutionMessage() + id);
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }
    }

    @Override
    public String description() {
        return "remove_by_id {id}: удалить элемент из коллекции по его id";
    }

    @Override
    public String successExecutionMessage() {
        return "Элемент успешно удален,его Id:";
    }


}