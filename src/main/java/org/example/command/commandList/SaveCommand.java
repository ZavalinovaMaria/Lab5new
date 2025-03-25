package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.fileWork.FileManager;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * сохранения коллекции в файл
 */
public class SaveCommand implements Command {
    private final GroupsCollectionManager collection;
    private final FileManager manager;

    public SaveCommand(GroupsCollectionManager collection) {
        this.collection = collection;
        this.manager = new FileManager();

    }

    /**
     * Выполняет команду сохранения коллекции в файл.
     * Метод получает путь к первоначальному файлу, затем сохраняет коллекцию в этот файл с использованием {@link FileManager}
     *
     * @param context контекст выполнения команды.
     */
    @Override
    public void execute(CommandContext context) {
        String filePath = collection.getFilePathCreatedFrom();
        manager.write(collection.getCollection(), filePath);
        System.out.println(successExecutionMessage());

    }

    @Override
    public String description() {
        return "save: коллекциию в файл";
    }

    @Override
    public String successExecutionMessage() {
        return "Коллекция успешно сохранена в файл";
    }

}