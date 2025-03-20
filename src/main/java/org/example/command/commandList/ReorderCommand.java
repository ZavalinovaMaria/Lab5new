package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import java.util.Collections;

public class ReorderCommand implements Command {
    GroupsCollectionManager collection;

    public ReorderCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        if(collection.getCountOfElements()>1){
            Collections.reverse(collection.getCollection());
            System.out.println(successExecution());}
        else{
            System.out.println("Недостаточно элементов для сортировки ");
        }
    }

    @Override
    public String description() {
        return "reorder: отсортировать коллекцию в порядке обратном нынешнему";
    }
    @Override
    public String successExecution() {
        return "Коллекция отсортирована,для просмотра изменений наберите show" ;
    }

}