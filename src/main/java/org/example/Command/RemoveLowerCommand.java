package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;

import java.util.Iterator;

public class RemoveLowerCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public RemoveLowerCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        int id;
        try {
            id = Integer.parseInt(context.getArgument());
            if (collection.checkingExistence(id,collection.getIdStorage())) {
                Iterator<StudyGroup> iterator = collection.getCollection().iterator();
                int count = collection.getCollection().get(collection.findIndexById(id)).getStudentsCount();
                while (iterator.hasNext()) {
                    StudyGroup group = iterator.next();
                    if (group.getStudentsCount() < count) {
                        //collection.delete(group.getId());  c итератором не дружит
                        iterator.remove();
                        collection.deleteId(group.getId(),collection.getIdStorage());
                        System.out.println("Элемет c id "+group.getId()+ " успешно удален ");
                    }
                }
                collection.updateData();
            }else{
                System.out.println("Коллекция осталась без изменений ");
            }

        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (NotExistingValueException e) {
        System.out.println("Элемент с введенным id не содержится в коллекции, ");
        }
    }

    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "remove_lower {id}: удалить из коллекции элементы меньшие чем заданный";
    }

}
