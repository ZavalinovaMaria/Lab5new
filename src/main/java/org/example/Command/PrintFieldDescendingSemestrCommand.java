package org.example.Command;

import org.example.console.CollectionManager;

public class PrintFieldDescendingSemestrCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public PrintFieldDescendingSemestrCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.printFieldDescendingSemestr();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "print_field_descending_semestr: вывести значения поля semestrEnum всех элементов в порядке убывания";
    }

}