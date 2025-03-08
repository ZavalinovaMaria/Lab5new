package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.Creator;
import org.example.console.GroupsCollectionManager;
import org.example.subjects.StudyGroup;

import java.time.DateTimeException;



public class AddCommand implements Command {
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;
    private final Creator creator = new Creator();

    public AddCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(CommandContext context) {
        if (context.isScriptWorking()) {
            try {
                StudyGroup newGroup = creator.toBuildGroupforScript(context.getScriptArguments());
                collection.add(newGroup);
                //if (collection.addNewId(newGroup.getId())) {
                   // collection.getCollection().add(newGroup);
//Почему нельзя сразу добавить элемент в коллекцию? не понятно будет ли выпадать исключение --если нет, то в коллекцию будет хуйня записываться и в хранидише
                   // clearCompositeCommand();
                //все вроде окей
                   // System.out.println("Новый объект добавлен в коллекцию");
               // }
            } catch (NumberFormatException E) {
                System.out.println("Ошибка заполнения полей ");
            } catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
                System.out.println("Не удалось добавить элемент из-за неккоректных данных");
            }
        } else {
            StudyGroup newGroup = creator.createGroup();
            collection.add(newGroup);
            //System.out.println("Новый объект добавлен в коллекцию");
        }
    }



    @Override
    public String description() {
        return "add : добавить новый элемент в коллекцию";
    }

}
