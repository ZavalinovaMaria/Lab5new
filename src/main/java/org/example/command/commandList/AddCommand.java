package org.example.command.commandList;


import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.InvalidValueException;
import org.example.subjects.StudyGroup;
import org.example.subjects.creators.StudyGroupsCreator;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * добавления новой учебной группы в коллекцию.
 */
public class AddCommand implements Command {
    GroupsCollectionManager collection;

    private final StudyGroupsCreator creator = new StudyGroupsCreator();

    public AddCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду добавления новой учебной группы в коллекцию.
     * Группа создаётся с помощью методов класса {@link StudyGroupsCreator}
     * Если команда выполняется в режиме скрипта(определяется через {@link CommandContext#isScriptWorking()}),
     * то группа создаётся на основе переданных аргументов контекста
     * Если команда выполняется в интерактивном режиме, группа создаётся с помощью консольного ввода.
     * В случае некорректных данных выбрасывается {@link InvalidValueException}.
     *
     * @param context Контекст выполнения команды, содержащий флаг режима скрипта и аргументы
     */
    @Override
    public void execute(CommandContext context) {
        if (context.isScriptWorking()) {
            try {
                StudyGroup newGroup = creator.createGroup(context.getScriptArguments(), context.isScriptWorking());
                collection.add(newGroup);
                System.out.println(successExecutionMessage() + newGroup.getId());
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage() + ", группа не будет собрана");
            }
        } else {
            StudyGroup newGroup = creator.createGroupFromConsole();
            collection.add(newGroup);
            System.out.println(successExecutionMessage() + newGroup.getId());
        }
    }

    @Override
    public String description() {
        return "add : добавить новый элемент в коллекцию";
    }

    @Override
    public String successExecutionMessage() {
        return "Элемент успешно добавлен,его Id:";
    }

}
