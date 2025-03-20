package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.subjects.StudyGroup;
import java.util.*;


public class PrintFieldDescendingSemestrCommand implements Command {
    GroupsCollectionManager collection;
    public PrintFieldDescendingSemestrCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        List<StudyGroup> sortable = new ArrayList<>(collection.getCollection());
        Collections.sort(sortable);
        System.out.println(successExecution());
        for (StudyGroup group : sortable) {
            System.out.println("Id группы:"+group.getId()+","+group.getSemesterEnum());
        }
    }

    @Override
    public String description() {
        return "print_field_descending_semestr: вывести значения поля semestrEnum всех элементов в порядке убывания";
    }
    @Override
    public String successExecution() {
        return "Вывод значения поля semesterEnum всех элементов в порядке убывания: " ;
    }

}