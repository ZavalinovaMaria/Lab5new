package org.example.console;

import org.example.command.Command;
import org.example.command.CommandContext;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import static org.example.console.Invoker.commands;

/**
 * Класс, предназначенный для обработки и выполнения команд из скриптов.
 * Скрипт-текстовый файл, который содержит последовательность команд и их аргументы.
 */
public class ScriptManager {
    private final File script;

    public ScriptManager(File script) {
        System.out.println("Выполнение скрипта");
        this.script = script;
    }

    /**
     * Выполняет команды, содержащиеся в скрипте.
     * .
     * Сначала читает содержимое файла и разбивает его на токены с помощью метода {@link #scriptToTokens()}.
     * Проходит по токенам в цикле, определяя команды и собирая дополнительные аргументы команды, пока не встретит новую команду.
     * Затем создаёт {@link CommandContext} .
     * Вызывает метод {@code execute()} соответствующей команды.
     */
    public void executeScript() {
        String[] tokens = scriptToTokens();
        int i = 0;
        while (i < tokens.length) {
            String token = tokens[i];
            Command command = commands.get(token);
            if (command == null) {
                System.out.println("Mусор в скрипте: " + token);
                i++;
                continue;
            }
            String argument = null;
            List<String> scriptArguments = new ArrayList<>();

            if (requiresArgument(token)) {
                if (i + 1 < tokens.length) {
                    argument = tokens[i + 1];
                    i++;
                }
            }
            // Собираем аргументы до следующей команды
            int startArgsIndex = i + 1;
            while (startArgsIndex < tokens.length) {
                String nextToken = tokens[startArgsIndex];
                if (commands.containsKey(nextToken)) {
                    break;
                }
                scriptArguments.add(nextToken);
                startArgsIndex++;
            }
            i = startArgsIndex;
            CommandContext context = new CommandContext(argument, true, scriptArguments.toArray(new String[0]));
            try {
                command.execute(context);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка выполнения команды '" + token + "' в скрипте: недостаточно аргументов");
            }
        }
    }

    /**
     * Проверяет, требует ли команда аргумент.
     *
     * @param commandName Название команды.
     * @return `true`, если команде требуется аргумент, иначе `false`.
     */
    private boolean requiresArgument(String commandName) {
        return commandName.equalsIgnoreCase("remove_lower") ||
                commandName.equalsIgnoreCase("filter_admin") ||
                commandName.equalsIgnoreCase("remove_by_id") ||
                commandName.equalsIgnoreCase("update_id") ||
                commandName.equalsIgnoreCase("remove_at") ||
                commandName.equalsIgnoreCase("execute_script");
    }

    /**
     * Читает скрипт из файла и разбивает его на токены.
     *
     * @return Массив строк, представляющих команды и их аргументы.
     */
    private String[] scriptToTokens() {
        try {
            List<String> lines = Files.readAllLines(script.toPath());
            List<String> tokens = new ArrayList<>();
            for (String line : lines) {
                tokens.addAll(Arrays.asList(line.trim().split("\\s+")));
            }
            return tokens.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения скрипта: " + e.getMessage());
            return new String[0];
        }
    }
}





