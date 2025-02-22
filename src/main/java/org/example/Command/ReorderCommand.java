package org.example.Command;

import org.example.console.CollectionManager;

public class ReorderCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public ReorderCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.reorder();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "reorder: отсортировать коллекцию в порядке обратном нынешнему";
    }

}