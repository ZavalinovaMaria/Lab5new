package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.InvalidValueException;
import org.example.subjects.StudyGroup;
import org.example.subjects.creators.StudyGroupsCreator;


public class AddCommand implements Command {
    GroupsCollectionManager collection;
    private final StudyGroupsCreator creator = new StudyGroupsCreator();

    public AddCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(CommandContext context) {
        if (context.isScriptWorking()) {
            try {
                StudyGroup newGroup = creator.createGroup(context.getScriptArguments(),context.isScriptWorking());
                collection.add(newGroup);
                System.out.println(successExecution());
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage() + ", группа не будет собрана");
            }
        } else {
            StudyGroup newGroup = creator.createGroupFromConsole();
            collection.add(newGroup);
        }
    }



    @Override
    public String description() {
        return "add : добавить новый элемент в коллекцию";
    }
    @Override
    public String successExecution() {
        return "Элемент успешно добавлен,его Id:" ;
    }

}
