package org.example.Command;

import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;

public class ShowCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public ShowCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        if (collection.getCountOfElements()==0) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println(collection.getCollection());
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

}