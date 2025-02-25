package org.example.console;
import org.example.Command.Command;
import org.example.Command.*;

import java.util.*;


public class Invoker {
    static Map<String, Command> commands = new HashMap<>();
    public final CollectionManager manager;


    public Invoker(String path) {
        this.manager = new CollectionManager(path);
    }

    public void init() {
        commands.put("help", new HelpCommand(manager));
        commands.put("add", new AddCommand(manager));
        commands.put("average_of_students_count", new AverageOfStudentsCountCommand(manager));
        commands.put("clear", new ClearCommand(manager));
        commands.put("execute_script", new ExecuteScriptCommand(manager));
        commands.put("exit", new ExitCommand(manager));
        commands.put("filter_admin", new FilterAdminCommand(manager));
        commands.put("info", new InfoCommand(manager));
        commands.put("print_field_descending_semestr", new PrintFieldDescendingSemestrCommand(manager));
        commands.put("remove_at", new RemoveAtCommand(manager));
        commands.put("remove_by_id", new RemoveByIdCommand(manager));
        commands.put("remove_lower", new RemoveLowerCommand(manager));
        commands.put("reorder", new ReorderCommand(manager));
        commands.put("save", new SaveCommand(manager));
        commands.put("show", new ShowCommand(manager));
        commands.put("update_id", new UpdateIdCommand(manager));

    }

    public CollectionManager getManager() {
        return manager;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }


    public void work() {
        while (true) {
            init();
            System.out.println("Введите команду");
            String query = null;
            try {
                Scanner scan = new Scanner(System.in);

                if (!scan.hasNextLine()) {
                    System.out.println("Завершить сеанс можно только с помощью команды exit.");
                    continue;
                }
                query = scan.nextLine();
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
            manager.setTokens(tokens);
            Command command = commands.get(tokens[0]);

            try {
                command.execute();
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

