package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;

public class ExitCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public ExitCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        System.out.println("Session ended");
        System.out.println("Конец текущей сессии");
        System.exit(0);;
    }
    /**
     * Method that returns command description
     * @return Command description
     */


    @Override
    public String description() {
        return "exit: завершить программу";
    }

}
