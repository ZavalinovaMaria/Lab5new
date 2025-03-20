package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
public class InfoCommand implements Command {
    GroupsCollectionManager collection;

    public InfoCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        System.out.print(successExecution());
        System.out.println(collection.toString());
    }
    @Override
    public String description() {
        return "info: вывести информацию о коллекции";
    }
    @Override
    public String successExecution() {
        return "Информация о коллекции:" ;
    }

}