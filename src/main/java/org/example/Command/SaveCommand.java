package org.example.Command;

import org.example.console.CollectionManager;

public class SaveCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public SaveCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.save();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "save: коллекциию в файл";
    }

}