package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;

public class ShowCommand implements Command {
    GroupsCollectionManager collection;

    public ShowCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        if (collection.getCountOfElements()==0) System.out.println("Коллекция пуста.");
        else {
            System.out.println(successExecution()+ collection.getCollection());
        }
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "show: вывести все элементы в строковом представлении";
    }
    @Override
    public String successExecution() {
        return "Вывод элементов коллекции" ;
    }

}