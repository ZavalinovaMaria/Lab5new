package org.example.Command;

import org.example.console.CollectionManager;

public class RemoveByIdCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public RemoveByIdCommand(CollectionManager manager) {
        this.manager= manager;
    }
    @Override
    public void execute(){
        manager.removeById();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "remove_by_id {id}: удалить элемент из коллекции по его id";
    }

}