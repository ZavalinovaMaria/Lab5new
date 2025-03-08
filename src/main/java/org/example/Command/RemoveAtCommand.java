package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

public class RemoveAtCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public RemoveAtCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
        int index;
        try {
            index = Integer.parseInt(context.getArgument());
            collection.deleteByIndex(index);
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Элемента с таким индексом нет в коллекции ");
        }
    }

    /**
     * Method that returns command description
     * @return Command description
     */
/*
    public void removeAt() {
        try {
            int index;
            if (isScriptWorking) {
                index = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                index = Integer.parseInt(tokens[1]);
            }
            try {
                collection.deleteId(collection.getCollection().get(index).getId());
                collection.getCollection().remove(index);
                collection.updateData();
                System.out.println("Элемент успешно удален ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Элемента с таким индексом нет в коллекции ");

            }} catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }
    }

 */
/**
 * Method that returns command description
 * @return Command description
 */
    @Override
    public String description() {
        return "remove_at {index}: удалить элемент находящийся в заданной позиции коллекции";
    }

}