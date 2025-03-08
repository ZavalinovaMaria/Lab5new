package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;

import java.util.Collections;

public class ReorderCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;

    public ReorderCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
            if(collection.getCountOfElements()>1){
                Collections.reverse(collection.getCollection());
                System.out.println("Коллекция отсортирована,для просмотра изменений наберите show");}
            else{
                System.out.println("Недостаточно элементов для сортировки ");
            }
       // }else System.out.println("В данной команде не нужен аргумент ");// для скрипта не работает
        // так как там сразу отсекаем все команды которые без аргумента по дефолту
    }
    /**
     * Method that returns command description
     * @return Command description
     */

    @Override
    public String description() {
        return "reorder: отсортировать коллекцию в порядке обратном нынешнему";
    }

}