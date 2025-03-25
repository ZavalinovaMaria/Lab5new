package org.example.command;

import org.example.command.commandList.*;
import org.example.utils.GroupsCollectionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, предназначенный для регистрации команд, связанных с коллекциями групп.
 * Хранит карту, в которой ключом является строка (имя команды), а значением - соответствующая команда.
 */
public class CommandRegistry {
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * Конструктор, инициализирующий карту команд. Каждое имя команды связано с соответствующим объектом команды.
     *
     * @param manager {@link GroupsCollectionManager}, менеджер коллекции групп, который передается в каждую команду.
     */
    public CommandRegistry(GroupsCollectionManager manager) {
        commandMap.put("help", new HelpCommand(manager));
        commandMap.put("add", new AddCommand(manager));
        commandMap.put("average_of_students_count", new AverageOfStudentsCountCommand(manager));
        commandMap.put("clear", new ClearCommand(manager));
        commandMap.put("execute_script", new ExecuteScriptCommand(manager));
        commandMap.put("exit", new ExitCommand(manager));
        commandMap.put("filter_admin", new FilterAdminCommand(manager));
        commandMap.put("info", new InfoCommand(manager));
        commandMap.put("print_field_descending_semestr", new PrintFieldDescendingSemestrCommand(manager));
        commandMap.put("remove_at", new RemoveAtCommand(manager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(manager));
        commandMap.put("remove_lower", new RemoveLowerCommand(manager));
        commandMap.put("reorder", new ReorderCommand(manager));
        commandMap.put("save", new SaveCommand(manager));
        commandMap.put("show", new ShowCommand(manager));
        commandMap.put("update_id", new UpdateIdCommand(manager));
    }

    public Map<String, Command> getCommands() {
        return commandMap;
    }
}
