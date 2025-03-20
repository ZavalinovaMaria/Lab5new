package org.example.command.commandList;
import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;

import java.util.Iterator;

public class RemoveLowerCommand implements Command {

    GroupsCollectionManager collection;

    public RemoveLowerCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        int id;
        try {
            id = Integer.parseInt(context.getArgument());
            if (collection.checkingExistenceOfId(id,collection.getIdStorage())) {
                Iterator<StudyGroup> iterator = collection.getCollection().iterator();
                int count = collection.getCollection().get(collection.findIndexById(id)).getStudentsCount();
                while (iterator.hasNext()) {
                    StudyGroup group = iterator.next();
                    if (group.getStudentsCount() < count) {
                        iterator.remove();
                        collection.deleteId(group.getId(),collection.getIdStorage());
                        System.out.println(successExecution()+group.getId());
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

    @Override
    public String description() {
        return "remove_lower {id}: удалить из коллекции элементы меньшие чем заданный";
    }
    @Override
    public String successExecution() {
        return "Элемент успешно удален,его Id:" ;
    }

}
