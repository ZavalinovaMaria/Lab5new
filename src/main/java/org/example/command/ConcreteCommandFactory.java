package org.example.command;

import org.example.command.commandList.*;
import org.example.collectionInstruments.GroupsCollectionManager;

public class ConcreteCommandFactory implements CommandFactory {
    @Override
    public Command createHelpCommand(GroupsCollectionManager manager) {
        return new HelpCommand(manager);
    }

    @Override
    public Command createAddCommand(GroupsCollectionManager manager) {
        return new AddCommand(manager);
    }

    @Override
    public Command createAverageOfStudentsCountCommand(GroupsCollectionManager manager) {
        return new AverageOfStudentsCountCommand(manager);
    }

    @Override
    public Command createClearCommand(GroupsCollectionManager manager) {
        return new ClearCommand(manager);
    }

    @Override
    public Command createExecuteScriptCommand(GroupsCollectionManager manager) {
        return new ExecuteScriptCommand(manager);
    }

    @Override
    public Command createExitCommand(GroupsCollectionManager manager) {
        return new ExitCommand(manager);
    }

    @Override
    public Command createFilterAdminCommand(GroupsCollectionManager manager) {
        return new FilterAdminCommand(manager);
    }

    @Override
    public Command createInfoCommand(GroupsCollectionManager manager) {
        return new InfoCommand(manager);
    }

    @Override
    public Command createPrintFieldDescendingSemestrCommand(GroupsCollectionManager manager) {
        return new PrintFieldDescendingSemestrCommand(manager);
    }

    @Override
    public Command createRemoveAtCommand(GroupsCollectionManager manager) {
        return new RemoveAtCommand(manager);
    }

    @Override
    public Command createRemoveByIdCommand(GroupsCollectionManager manager) {
        return new RemoveByIdCommand(manager);
    }

    @Override
    public Command createRemoveLowerCommand(GroupsCollectionManager manager) {
        return new RemoveLowerCommand(manager);
    }

    @Override
    public Command createReorderCommand(GroupsCollectionManager manager) {
        return new ReorderCommand(manager);
    }

    @Override
    public Command createSaveCommand(GroupsCollectionManager manager) {
        return new SaveCommand(manager);
    }

    @Override
    public Command createShowCommand(GroupsCollectionManager manager) {
        return new ShowCommand(manager);
    }

    @Override
    public Command createUpdateIdCommand(GroupsCollectionManager manager) {
        return new UpdateIdCommand(manager);
    }
}

