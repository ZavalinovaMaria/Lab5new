package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;
import java.util.ArrayList;

public class FilterAdminCommand implements Command {

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
        ArrayList<StudyGroup> requiredElements = new ArrayList<>();
        for (StudyGroup group : collection.getCollection()) {
            String value = group.getGroupAdmin().getName();
            if (value.equals(admin)) {
                requiredElements.add(group);
            }
        }
        if (requiredElements.isEmpty()) {
            System.out.println("В коллекции нет элементов, содержащих такое имя старосты ");
        } else {
            System.out.println(successExecution());
            for (StudyGroup element : requiredElements) {
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
    @Override
    public String successExecution() {
        return "Данное имя старосты имеют следущие элементы:" ;
    }

}
