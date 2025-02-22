package org.example.Command;

import org.example.console.CollectionManager;

public class UpdateIdCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public UpdateIdCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.updateId();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "updateId {element}: обновить значение элемента id которого равен заданному";
    }

}