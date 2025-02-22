package org.example.Command;

import org.example.console.CollectionManager;

public class RemoveAtCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public RemoveAtCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.removeAt();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "removeAt: удалить элемент находящийся в заданной позиции коллекции";
    }

}