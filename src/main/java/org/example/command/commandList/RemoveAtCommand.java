package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * удаления элемента коллекции по указанному индексу
 */
public class RemoveAtCommand implements Command {
    GroupsCollectionManager collection;

    public RemoveAtCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }


    /**
     * Метод получает аргумент команды из {@link CommandContext#getArgument()} и пытается преобразовать его в целое число.
     * Если аргумент отсутствует, выводится сообщение об ошибке (исключение {@link NullValueException}).
     * Если аргумент не является числом, выводится сообщение о необходимости передачи числового значения.
     * При корректном значении происходит удаление элемента по индексу.
     * Если индекс выходит за пределы коллекции, выводится сообщение об ошибке.
     *
     * @param context контекст выполнения команды, содержащий аргумент в виде строки.
     */
    @Override
    public void execute(CommandContext context) {
        try {
            int index = Integer.parseInt(context.getArgument());
            collection.deleteByIndex(index);
            System.out.println(successExecutionMessage() + index);
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Индекс должен быть числом ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Элемента с таким индексом нет в коллекции ");
        }
    }

    @Override
    public String description() {
        return "remove_at {index}: удалить элемент находящийся в заданной позиции коллекции";
    }

    @Override
    public String successExecutionMessage() {
        return "Элемент успешно удален,его старый индекс:";
    }

}