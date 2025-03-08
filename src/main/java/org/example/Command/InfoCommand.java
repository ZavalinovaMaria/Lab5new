package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;

public class InfoCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public InfoCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        System.out.print("Collection information:");
        System.out.println(collection.toString());
    }
    /**
     * Method that returns command description
     * @return Command description
     */


    @Override
    public String description() {
        return "info: вывести информацию о коллекции";
    }

}