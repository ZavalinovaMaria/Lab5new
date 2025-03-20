package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.console.Invoker;


public class HelpCommand implements Command {
    GroupsCollectionManager collection;
    public HelpCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        System.out.println(successExecution());
        for (Command command : Invoker.getCommands().values()) {
            System.out.println(command.description());
        }
    }
    @Override
    public String description() {
        return "help: вывести справку по доступным командам";
    }
    @Override
    public String successExecution() {
        return "Справка по доступным командам:" ;
    }

}