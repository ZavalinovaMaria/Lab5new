package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

public class RemoveAtCommand implements Command {
    GroupsCollectionManager collection;

    public RemoveAtCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
        try {
            int index = Integer.parseInt(context.getArgument());
            collection.deleteByIndex(index);
            System.out.println(successExecution()+index);
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Элемента с таким индексом нет в коллекции ");
        }
    }
    @Override
    public String description() {
        return "remove_at {index}: удалить элемент находящийся в заданной позиции коллекции";
    }
    @Override
    public String successExecution() {
        return "Элемент успешно удален,его старый индекс:" ;
    }

}