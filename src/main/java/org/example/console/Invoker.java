package org.example.console;
import org.example.Command.Command;
import org.example.Command.*;

import java.util.*;


public class Invoker {
     static Map<String, Command> commands = new HashMap<>();
    public final CollectionManager manager;


    public Invoker(String path) {
        this.manager = new CollectionManager(path);
        //по идее тут создавались всякие менеджеры для команд
    }

    public void init() {
        commands.put("help", new HelpCommand(manager));
        commands.put("add", new AddCommand(manager));
        commands.put("average_of_students_count", new AverageOfStudentsCountCommand(manager));
        commands.put("clear", new ClearCommand(manager));
        commands.put("execute_Script", new ExecuteScriptCommand(manager));
        commands.put("exit", new ExitCommand(manager));
        commands.put("filter_Admin", new FilterAdminCommand(manager));
        commands.put("info", new InfoCommand(manager));
        commands.put("print_Field_Descending_Semestr", new PrintFieldDescendingSemestrCommand(manager));
        commands.put("remove_At", new RemoveAtCommand(manager));
        commands.put("remove_By_Id", new RemoveByIdCommand(manager));
        commands.put("remove_Lower", new RemoveLowerCommand(manager));
        commands.put("reorder", new ReorderCommand(manager));
        commands.put("save", new SaveCommand(manager));
        commands.put("show", new ShowCommand(manager));
        commands.put("update_Id", new UpdateIdCommand(manager));

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
                query = scan.nextLine().toLowerCase();
            } catch (NoSuchElementException e) {
                System.out.println("Пустая строка, выход из программы");
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
                manager.addToHistory(String.valueOf(command));
            } catch (NullPointerException e) {
                System.out.println("Введенная команда не существует");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Недопустимый формат команды");
            }

        }
    }
}
