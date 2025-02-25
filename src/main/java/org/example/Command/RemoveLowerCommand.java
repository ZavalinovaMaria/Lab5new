package org.example.Command;

import org.example.console.CollectionManager;

public class RemoveLowerCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public RemoveLowerCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.removeLower();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "remove_lower {id}: удалить из коллекции элементы меньшие чем заданный";
    }

}
