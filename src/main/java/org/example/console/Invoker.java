package org.example.console;

import org.example.utils.GroupsCollectionManager;
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
    private final CommandRegistry commandRegistry;

    public Invoker(GroupsCollectionManager manager) {
        this.manager = manager;
        this.commandRegistry = new CommandRegistry(manager);

    }

    /**
     * Получает список команд из {@link CommandRegistry}
     */
    private void init() {
        commands = commandRegistry.getCommands();
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Запускает основной цикл обработки пользовательского ввода.
     * <p>
     * Метод выполняет следующие шаги:
     * Инициализирует команды с помощью {@link #init()}.
     * Запрашивает ввод команды у пользователя.
     * Если введена команда "exit", программа завершается.
     * Если команда найдена, создаёт контекст с помощью {@link CommandContext} и выполняет её.
     * Обрабатывает возможные ошибки ввода и выполняет соответствующие действия.
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

