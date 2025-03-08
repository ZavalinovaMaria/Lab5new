package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

public class RemoveByIdCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public RemoveByIdCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
        int id;
        try {
            id = Integer.parseInt(context.getArgument());
            collection.deleteById(id);
           // System.out.println("Элемент успешно удален ");
            //} else System.out.println("Не удалось удалить элемент ");

        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }
    }




    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "remove_by_id {id}: удалить элемент из коллекции по его id";
    }



}