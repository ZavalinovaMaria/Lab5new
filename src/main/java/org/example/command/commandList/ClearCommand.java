package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;

public class ClearCommand implements Command {

    GroupsCollectionManager collection;

    public ClearCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        collection.getCollection().clear();
        collection.getIdStorage().clear();
        collection.updateData();
        System.out.println(successExecution());
    }

    @Override
    public String description() {
        return "clear: очистить коллекцию";
    }
    @Override
    public String successExecution() {
        return "Коллекция пуста" ;
    }

}

