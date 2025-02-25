package org.example.Command;

import org.example.console.CollectionManager;

public class FilterAdminCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public FilterAdminCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.filterAdmin();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "filter_admin {name}: вывести элементы, значения поля groupAdmin которых равно заданному";
    }

}
