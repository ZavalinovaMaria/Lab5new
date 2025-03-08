package org.example.console;

import org.example.Command.Command;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

import static org.example.console.Invoker.commands;

public class ScriptManager {
        private File script;
        private GroupsCollectionManager manager;
    private final Set<String> scriptHistory = new HashSet<>();

    public ScriptManager(File script, GroupsCollectionManager manager) {
            System.out.println("Выполнение скрипта");
            this.script = script;
            this.manager = manager;
        }
    public void executeScript() {
            String[] tokens = scriptToTokens();
            int i = 0;

            while (i < tokens.length) {
                String token = tokens[i];

                // Проверяем — команда ли это
                Command command = commands.get(token);
                if (command == null) {
                    System.out.println("Неизвестная команда или мусор в скрипте: " + token);
                    i++; // пропускаем некомандный токен
                    continue;
                }

                // Если это команда — начинаем обрабатывать
                String commandName = token;
                String argument = null;
                List<String> scriptArguments = new ArrayList<>();

                // Если команде нужен ровно 1 аргумент (например, remove_by_id)
                if (requiresArgument(commandName)) {
                    if (i + 1 < tokens.length) {
                        argument = tokens[i + 1];
                        i++; // съели аргумент
                    }
                }

                // Собираем аргументы до следующей команды
                int startArgsIndex = i + 1;
                while (startArgsIndex < tokens.length) {
                    String nextToken = tokens[startArgsIndex];
                    if (commands.containsKey(nextToken)) {
                        // Если нашли следующую команду — выходим из цикла сбора аргументов
                        break;
                    }
                    scriptArguments.add(nextToken);
                    startArgsIndex++;
                }

                // Обновляем i до начала следующей команды
                i = startArgsIndex;

                // Создаем контекст и выполняем команду
                CommandContext context = new CommandContext(argument, true, scriptArguments.toArray(new String[0]));

                try {
                    command.execute(context);
                } catch (Exception e) {
                    System.out.println("Ошибка выполнения команды '" + commandName + "' в скрипте: " + e.getMessage());
                }

                // i уже стоит на следующей команде или на конце, так что ничего не нужно делать
            }
        }




        private boolean requiresArgument(String commandName) {
        return
                commandName.equalsIgnoreCase("remove_lower") ||
                commandName.equalsIgnoreCase("filter_admin") ||
                commandName.equalsIgnoreCase("remove_by_id") ||
                commandName.equalsIgnoreCase("update_id") ||
                commandName.equalsIgnoreCase("remove_at") ||
                commandName.equalsIgnoreCase("execute_script") ;
    }


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


        /*
        public void executeScript() {
            String[] tokens = scriptToTokens();
            for (int i = 0; i < tokens.length; i++) {
                try {
                    Command command = commands.get(tokens[i]);
                    if (tokens[i].equalsIgnoreCase("remove_by_id") || tokens[i].equalsIgnoreCase("update_id") ||
                            tokens[i].equalsIgnoreCase("add") || tokens[i].equalsIgnoreCase("remove_at") ||
                            tokens[i].equalsIgnoreCase("execute_script") || tokens[i].equalsIgnoreCase("remove_lower") ||
                            tokens[i].equalsIgnoreCase("filter_admin")) {
                        manager.setCompositeCommand(Arrays.copyOfRange(tokens, i + 1, tokens.length));
                    }
                    boolean isTokenCommand = commands.containsKey(tokens[i]);
                    if (!isTokenCommand) {
                        continue;
                    }
                    command.execute();
                } catch (NullPointerException e) {
                    System.err.println("Ошибка исполнения скрипта");
                }
            }
        }

        /**
         * A method that reads a script file and converts it to a char array and return result of the {@link ScriptManager#getStrings(char[])}
         *
         * @return Command array
         */
    /*
        private String[] scriptToTokens() {
            File file = new File(script.toString());
            char[] inputChar = new char[(int) file.length()];
            try (InputStreamReader reader = new FileReader(script)) {
                reader.read(inputChar);
                reader.close();
                return getStrings(inputChar);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не обнаружен");
                return new String[0];
            } catch (IOException e) {
                System.out.println("IO exception");
                return new String[0];
            }
        }

        /**
         * Private method that turns an array of characters into an array of strings with individual commands
         *
         * @param inputChar An array of characters read from a file with a script method{@link ScriptManager#scriptToTokens()}
         * @return Command array
         */
/*
        private static String[] getStrings(char[] inputChar) {
            List<String> tokens = new ArrayList<>();
            StringBuilder token = new StringBuilder();

            for (char ch : inputChar) {
                if ((ch == ' ') || (ch == '\n') || ch == '\r' || ch == '\0') {
                    if (token.length() > 0) {
                        tokens.add(token.toString());
                        token.setLength(0);
                    }
                } else {
                    token.append(ch);
                }
            }
            if (token.length() > 0) {
                tokens.add(token.toString());
            }
            return tokens.toArray(new String[0]);
        }



 */


