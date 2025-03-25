package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.exceptions.NotExistingValueException;
import org.example.exceptions.NullValueException;
import org.example.subjects.StudyGroup;

import java.util.Iterator;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * удаления из коллекции всех элементов, у которых значение поля {@code studentsCount} меньше, чем значение поля того элемента, id которого передан в аргументе.
 */
public class RemoveLowerCommand implements Command {

    GroupsCollectionManager collection;

    public RemoveLowerCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду удаления из коллекции всех элементов, у которых значение поля
     * {@code studentsCount} меньше, чем значение поля того элемента, id которого передан в аргументе.
     * <p>
     * Метод выполняет следующие действия:
     * <ol>
     *   <li>Получает строковый аргумент из {@link CommandContext} и преобразует его в число.
     *       Если аргумент отсутствует, выбрасывается {@link NullValueException}.</li>
     *   <li>Проверяет, существует ли элемент с заданным id в коллекции, с помощью метода
     *       {@code checkingExistenceOfId}.</li>
     *   <li>Если элемент с указанным id найден, определяется значение {@code studentsCount} этого элемента.</li>
     *   <li>С помощью итератора перебираются все элементы коллекции. Если значение поля
     *       {@code studentsCount} текущего элемента меньше заданного, элемент удаляется,
     *       а его id удаляется из хранилища с помощью метода {@code deleteId}.</li>
     *   <li>После итерации вызывается метод {@code updateData()} для обновления состояния коллекции.</li>
     *   <li>Если элемент с заданным id отсутствует, выводится сообщение о том, что коллекция не была изменена.</li>
     * </ol>
     * При возникновении ошибок, таких как неверный формат аргумента (NumberFormatException) или
     * отсутствие элемента с указанным id (NotExistingValueException), выводятся соответствующие сообщения.
     * </p>
     *
     * @param context контекст выполнения команды, содержащий аргумент (идентификатор) в виде строки.
     */
    @Override
    public void execute(CommandContext context) {
        int id;
        try {
            id = Integer.parseInt(context.getArgument());
            if (collection.checkingExistenceOfId(id, collection.getIdStorage())) {
                if (collection.getCountOfElements() == 1) {
                    System.out.println("Коллекция осталась без изменений, так как в ней всего 1 элемент ");
                }
                Iterator<StudyGroup> iterator = collection.getCollection().iterator();
                int count = collection.getCollection().get(collection.findIndexById(id)).getStudentsCount();
                while (iterator.hasNext()) {
                    StudyGroup group = iterator.next();
                    if (group.getStudentsCount() < count) {
                        iterator.remove();
                        collection.deleteId(group.getId(), collection.getIdStorage());
                        System.out.println(successExecutionMessage() + group.getId());
                    }
                }
                collection.updateData();
            } else {
                System.out.println("Коллекция осталась без изменений");
            }
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с введенным id не содержится в коллекции");
        }
    }

    @Override
    public String description() {
        return "remove_lower {id}: удалить из коллекции элементы меньшие чем заданный";
    }

    @Override
    public String successExecutionMessage() {
        return "Элемент успешно удален,его Id:";
    }

}
