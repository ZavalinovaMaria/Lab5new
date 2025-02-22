package org.example.Command;

import org.example.console.CollectionManager;

public class AverageOfStudentsCountCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public AverageOfStudentsCountCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.averageOfStudentsCount();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "average_of_students_count: вывести среднее значение поля studentsCount для всех элементов коллекции ";
    }

}