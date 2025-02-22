package org.example.Command;

import org.example.console.CollectionManager;

public class AddCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public AddCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.add();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "add {element}: добавить новый элемент в коллекцию";
    }

}
