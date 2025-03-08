package org.example.Command;

import org.example.console.CommandContext;
import org.example.console.Creator;
import org.example.console.GroupsCollectionManager;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;

import java.time.DateTimeException;

public class UpdateIdCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;
    private final Creator creator = new Creator();

    public UpdateIdCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        try {
            int id;
            try{
                id = Integer.parseInt(context.getArgument());
            }catch (NullValueException e){
                System.out.println(e.getMessage());
                return;
            }
            if (context.isScriptWorking()) {
                if (collection.checkingExistence(id,collection.getIdStorage())) {
                    creator.toBuildUpdationGroup(collection.getCollection().get(collection.findIndexById(id)), context.getScriptArguments());
                    //clearCompositeCommand();
                    System.out.println("Значение элемента успешно обновлено");
                }
            } else {
                if (collection.checkingExistence(id,collection.getIdStorage())) {
                    creator.toUpdateGroup(collection.getCollection().get(collection.findIndexById(id)));
                    System.out.println("Значение элемента успешно обновлено");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        }catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
            System.out.println("Не удалось обновить  элемент из-за неккоректных данных");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным  не id обнаружен ");
        }
    }

    /**
     * Method that returns command description
     * @return Command description
     */


    @Override
    public String description() {
        return "update_id {id}: обновить значение элемента id которого равен заданному";
    }

}