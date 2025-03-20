package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.subjects.StudyGroup;

/**
 * Команда для вычисления среднего значения поля studentsCount среди всех учебных групп.
 * Реализует интерфейс {@link Command}.
 */
public class AverageOfStudentsCountCommand implements Command {

    GroupsCollectionManager collection;


    public AverageOfStudentsCountCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
            int startAverageCount = 0;
            for (StudyGroup group : collection.getCollection()) {
                startAverageCount += group.getStudentsCount();
            }
            System.out.print(successExecution()+ startAverageCount / collection.getCountOfElements());
    }


    /**
     * Method that returns command description
     * @return Command description
     */
    //@return строка с описанием команды.-поменять на это

    @Override
    public String description() {
        return "average_of_students_count: вывести среднее значение поля studentsCount для всех элементов коллекции ";
    }
    @Override
    public String successExecution() {
        return "Среднее значение поля studentsCount:" ;
    }

}