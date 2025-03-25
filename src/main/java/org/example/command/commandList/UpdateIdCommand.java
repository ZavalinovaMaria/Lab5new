package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.InvalidValueException;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;
import org.example.subjects.creators.StudyGroupsCreator;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * обновления значения элемента коллекции по заданному id
 */
public class UpdateIdCommand implements Command {
    GroupsCollectionManager collection;
    private final StudyGroupsCreator creator = new StudyGroupsCreator();

    public UpdateIdCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }


    /**
     * Выполняет команду обновления значения элемента коллекции по заданному идентификатору.
     * <p>
     * Метод выполняет следующие шаги:
     * <ol>
     *   <li>Получает аргумент из {@link CommandContext#getArgument()} и преобразует его в число. Если аргумент отсутствует,
     *       выбрасывается {@link NullValueException}.</li>
     *   <li>Проверяет, существует ли элемент с указанным идентификатором в коллекции с использованием метода
     *       {@code checkingExistenceOfId}.</li>
     *   <li>Если элемент найден, то:
     *       <ul>
     *         <li>Если команда выполняется в режиме скрипта (метод {@code isScriptWorking()} возвращает {@code true}),
     *             производится обновление элемента с помощью метода {@code fillGroup} и переданных аргументов скрипта.</li>
     *         <li>Если команда выполняется в интерактивном режиме, производится обновление элемента методом
     *             {@code fillGroupFromConsole}.</li>
     *       </ul>
     *   </li>
     *   <li>Выводится сообщение об успешном выполнении команды с указанием идентификатора обновлённого элемента.</li>
     * </ol>
     * В случае возникновения ошибок, выводятся соответствующие сообщения.
     * </p>
     *
     * @param context контекст выполнения команды, содержащий аргументы для обновления элемента.
     */

    @Override
    public void execute(CommandContext context) {
        try {
            int id;
            id = Integer.parseInt(context.getArgument());
            if (collection.checkingExistenceOfId(id, collection.getIdStorage())) {
                if (context.isScriptWorking()) {
                    creator.fillGroup(collection.getCollection().get(collection.findIndexById(id)), context.getScriptArguments(), -1);
                } else {
                    creator.fillGroupFromConsole(collection.getCollection().get(collection.findIndexById(id)));
                }
            }
            System.out.println(successExecutionMessage() + id);
        } catch (InvalidValueException e) {
            System.out.println(e.getMessage() + ",значение элемента не будет обновлено");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным  не id обнаружен ");
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Недопустимый тип аргумента");
        }
    }

    @Override
    public String description() {
        return "update_id {id}: обновить значение элемента id которого равен заданному";
    }

    @Override
    public String successExecutionMessage() {
        return "Значение успешно обновлено. Id:";
    }

}