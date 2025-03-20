package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
public class ExitCommand implements Command {

    GroupsCollectionManager collection;

    public ExitCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        System.out.println(successExecution());
        System.exit(0);
    }


    @Override
    public String description() {
        return "exit: завершить программу";
    }
    @Override
    public String successExecution() {
        return "Конец текущей сессии" ;
    }

}
