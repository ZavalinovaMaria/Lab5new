package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.subjects.StudyGroup;

public class AverageOfStudentsCountCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public AverageOfStudentsCountCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
            int averageCount = 0;
            for (StudyGroup group : collection.getCollection()) {
                averageCount += group.getStudentsCount();
            }
            System.out.println("Среднее значение поля studentsCount: " + averageCount / collection.getCountOfElements());
       // для скрипта не работает
// так как там сразу отсекаем все команды которые без аргумента по дефолту
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