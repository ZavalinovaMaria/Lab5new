package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.InvalidValueException;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;
import org.example.subjects.creators.StudyGroupsCreator;

public class UpdateIdCommand implements Command {
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;
    private final StudyGroupsCreator creator = new StudyGroupsCreator();

    public UpdateIdCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        try {
            int id;
            id = Integer.parseInt(context.getArgument());
            if (collection.checkingExistenceOfId(id,collection.getIdStorage())) {
                if (context.isScriptWorking()) {
                    creator.fillGroup(collection.getCollection().get(collection.findIndexById(id)), context.getScriptArguments(),-1);
                } else {
                creator.fillGroupFromConsole(collection.getCollection().get(collection.findIndexById(id)));
                }
            }
            System.out.println(successExecution()+id);
        } catch (InvalidValueException e){
            System.out.println(e.getMessage()+",значение элемента не будет обновлено");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным  не id обнаружен ");
        }catch (NullValueException e) {
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Недопустимый тип аргумента");
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
    @Override
    public String successExecution() {
        return "Значение успешно обновлено. Id:" ;
    }

}