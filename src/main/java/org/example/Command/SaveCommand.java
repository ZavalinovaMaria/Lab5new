package org.example.Command;


import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.console.Invoker;
import org.example.fileWork.FileManager;



public class SaveCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    private GroupsCollectionManager collection;
    private final FileManager manager;

    public SaveCommand(GroupsCollectionManager collection) {
        this.collection = collection;
        this.manager = new FileManager();

    }
    @Override
    public void execute(CommandContext context){
        String filePath = collection.getCreateFromThisFilePath();
        manager.write(collection.getCollection(),filePath);

    }
    /**
     * Method that returns command description
     * @return Command description
     */

    @Override
    public String description() {
        return "save: коллекциию в файл";
    }

}