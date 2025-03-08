package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;

public class ClearCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public ClearCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        collection.getCollection().clear();
        collection.getIdStorage().clear();
        collection.updateData();
        System.out.println("Коллекция пуста");
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "clear: очистить коллекцию";
    }

}

