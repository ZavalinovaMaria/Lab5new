package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.fileWork.FileManager;

public class SaveCommand implements Command {
    private final GroupsCollectionManager collection;
    private final FileManager manager;

    public SaveCommand(GroupsCollectionManager collection) {
        this.collection = collection;
        this.manager = new FileManager();

    }
    @Override
    public void execute(CommandContext context){
        String filePath = collection.getFilePathCreatedFrom();
        manager.write(collection.getCollection(),filePath);
        System.out.println(successExecution());

    }
    /**
     * Method that returns command description
     * @return Command description
     */

    @Override
    public String description() {
        return "save: коллекциию в файл";
    }
    @Override
    public String successExecution() {
        return "Коллекция успешно сохранена в файл" ;
    }

}