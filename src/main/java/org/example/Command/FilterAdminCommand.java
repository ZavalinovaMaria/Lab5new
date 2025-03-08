package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;

import java.util.ArrayList;

public class FilterAdminCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public FilterAdminCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context) {
        String admin;
        try{
         admin = context.getArgument();
        }catch (NullValueException e){
            System.out.println(e.getMessage());
            return;
        }

        ArrayList<StudyGroup> elements = new ArrayList<>();
            for (StudyGroup group : collection.getCollection()) {
                if (group.getGroupAdmin() != null) {
                    String value = group.getGroupAdmin().getName();
                    if (value.equals(admin)) {
                        elements.add(group);
                    }
                }
            }
            if (elements.isEmpty()) {
                System.out.println("В коллекции нет элементов, содержащих такое имя старосты ");
            } else {
                System.out.println("Данное имя старосты имеют следущие элементы:");
                for (StudyGroup element : elements) {
                    System.out.println(element);
                }
            }

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
