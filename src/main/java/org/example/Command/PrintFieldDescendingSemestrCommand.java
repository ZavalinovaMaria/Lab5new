package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.subjects.StudyGroup;
import org.example.subjects.comporators.ComparatorStudentsCount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingSemestrCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public PrintFieldDescendingSemestrCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        List<StudyGroup> sortable = new ArrayList<>(collection.getCollection());
        Comparator<StudyGroup> sortibility = new ComparatorStudentsCount();
        sortable.sort(sortibility);
        Collections.reverse(sortable);
        System.out.println("Вывод значения поля semesterEnum всех элементов в порядке убывания: ");
        for (StudyGroup group : sortable) {
            System.out.println("Id группы:"+group.getId()+","+group.getSemesterEnum());
        }
    }


    @Override
    public String description() {
        return "print_field_descending_semestr: вывести значения поля semestrEnum всех элементов в порядке убывания";
    }

}