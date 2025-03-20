package org.example.console;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.command.Command;
import org.example.command.*;

import java.util.*;

/**
 * Класс Invoker отвечает за обработку пользовательского ввода и выполнение команд.
 * Он хранит список доступных команд, а также запускает основной цикл обработки команд.
 */
public class Invoker {
    static Map<String, Command> commands = new HashMap<>();
    public final GroupsCollectionManager manager;
    private final CommandFactory commandFactory;



    public Invoker(GroupsCollectionManager manager,CommandFactory commandFactory) {
        this.manager = manager;
        this.commandFactory = commandFactory;
    }

    /**
     * Инициализирует команды и добавляет их в список доступных команд.
     * Каждая команда создаётся с помощью {@link CommandFactory} и передаётся в коллекцию команд.
     */
    private void init() {
        commands.put("help", commandFactory.createHelpCommand(manager));
        commands.put("add", commandFactory.createAddCommand(manager));
        commands.put("average_of_students_count", commandFactory.createAverageOfStudentsCountCommand(manager));
        commands.put("clear", commandFactory.createClearCommand(manager));
        commands.put("execute_script", commandFactory.createExecuteScriptCommand(manager));
        commands.put("exit", commandFactory.createExitCommand(manager));
        commands.put("filter_admin", commandFactory.createFilterAdminCommand(manager));
        commands.put("info", commandFactory.createInfoCommand(manager));
        commands.put("print_field_descending_semestr", commandFactory.createPrintFieldDescendingSemestrCommand(manager));
        commands.put("remove_at", commandFactory.createRemoveAtCommand(manager));
        commands.put("remove_by_id", commandFactory.createRemoveByIdCommand(manager));
        commands.put("remove_lower", commandFactory.createRemoveLowerCommand(manager));
        commands.put("reorder", commandFactory.createReorderCommand(manager));
        commands.put("save", commandFactory.createSaveCommand(manager));
        commands.put("show", commandFactory.createShowCommand(manager));
        commands.put("update_id", commandFactory.createUpdateIdCommand(manager));
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
    /**
     * Запускает основной цикл обработки пользовательского ввода.
     * <p>
     * Метод выполняет следующие шаги:
     * <ul>
     *     <li>Инициализирует команды с помощью {@link #init()}.</li>
     *     <li>Запрашивает ввод команды у пользователя.</li>
     *     <li>Если введена команда "exit", программа завершается.</li>
     *     <li>Если команда найдена, создаёт контекст  с помощью {@link CommandContext} и выполняет её.</li>
     *     <li>Обрабатывает возможные ошибки ввода и выполняет соответствующие действия.</li>
     * </ul>
     */
    public void work() {
        while (true) {
            init();
            System.out.println("Введите команду");
            String query;
            try {
                Scanner scan = new Scanner(System.in);
                if (!scan.hasNextLine()) {
                    System.out.println("Завершить сеанс можно только с помощью команды exit.");
                    continue;
                }
                query = scan.nextLine().trim();
            } catch (NoSuchElementException e) {
                System.out.println("Завершить сеанс можно только с помощью команды exit.");
                continue;
            }
            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена.");
                break;
            }

            String[] tokens = query.split(" ");
            if (tokens.length == 0) {
                tokens = query.split("\n");
            }
            Command command = commands.get(tokens[0]);

            try {
                String argument = tokens.length > 1 ? tokens[1] : null;
                CommandContext context = new CommandContext(argument, false, null);
                command.execute(context);
            } catch (NullPointerException e) {
                System.out.println("Введенная команда не существует");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Недопустимый формат команды");
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Завершить сеанс можно только с помощью команды exit.");
            }
        }
    }
}

