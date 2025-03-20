package org.example.command;

import org.example.collectionInstruments.GroupsCollectionManager;

public interface CommandFactory {
    Command createHelpCommand(GroupsCollectionManager manager);
    Command createAddCommand(GroupsCollectionManager manager);
    Command createAverageOfStudentsCountCommand(GroupsCollectionManager manager);
    Command createClearCommand(GroupsCollectionManager manager);
    Command createExecuteScriptCommand(GroupsCollectionManager manager);
    Command createExitCommand(GroupsCollectionManager manager);
    Command createFilterAdminCommand(GroupsCollectionManager manager);
    Command createInfoCommand(GroupsCollectionManager manager);
    Command createPrintFieldDescendingSemestrCommand(GroupsCollectionManager manager);
    Command createRemoveAtCommand(GroupsCollectionManager manager);
    Command createRemoveByIdCommand(GroupsCollectionManager manager);
    Command createRemoveLowerCommand(GroupsCollectionManager manager);
    Command createReorderCommand(GroupsCollectionManager manager);
    Command createSaveCommand(GroupsCollectionManager manager);
    Command createShowCommand(GroupsCollectionManager manager);
    Command createUpdateIdCommand(GroupsCollectionManager manager);
}
