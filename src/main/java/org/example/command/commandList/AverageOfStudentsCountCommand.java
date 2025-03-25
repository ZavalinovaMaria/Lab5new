package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.subjects.StudyGroup;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * вычисления среднего значения поля studentsCount среди всех учебных групп.
 */
public class AverageOfStudentsCountCommand implements Command {

    GroupsCollectionManager collection;


    public AverageOfStudentsCountCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду вычисления среднего значения поля {@code studentsCount}.
     * Метод проходит по всем элементам коллекции, суммирует значение поля {@code studentsCount} для каждого элемента,
     * а затем выводит сообщение об успешном выполнении команды, содержащее вычисленное среднее значение.
     *
     * @param context контекст выполнения команды, содержащий информацию о параметрах выполнения.
     */
    @Override
    public void execute(CommandContext context) {
        int startAverageCount = 0;
        for (StudyGroup group : collection.getCollection()) {
            startAverageCount += group.getStudentsCount();
        }
        System.out.print(successExecutionMessage() + startAverageCount / collection.getCountOfElements());
    }

    @Override
    public String description() {
        return "average_of_students_count: вывести среднее значение поля studentsCount для всех элементов коллекции ";
    }

    @Override
    public String successExecutionMessage() {
        return "Среднее значение поля studentsCount:";
    }
}