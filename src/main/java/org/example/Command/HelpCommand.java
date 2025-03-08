package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.console.Invoker;


public class HelpCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public HelpCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        for (Command com : Invoker.getCommands().values()) {
            System.out.println(com.description());
    }}
    /**
     * Method that returns command description
     * @return Command description
     */


    @Override
    public String description() {
        return "help: вывести справку по доступным командам";
    }

}