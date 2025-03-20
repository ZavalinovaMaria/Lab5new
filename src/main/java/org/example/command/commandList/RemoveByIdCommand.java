package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.NullValueException;

public class RemoveByIdCommand implements Command {
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
            System.out.println(successExecution());
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
    @Override
    public String successExecution() {
        return "Элемент успешно удален,его Id:" ;
    }


}