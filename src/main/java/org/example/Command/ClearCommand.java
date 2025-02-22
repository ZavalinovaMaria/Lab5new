package org.example.Command;

import org.example.console.CollectionManager;

public class ClearCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public ClearCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.clear();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "clear: очистить коллекцию";
    }

}

