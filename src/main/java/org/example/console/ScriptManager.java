package org.example.console;

import org.example.Command.Command;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.console.Invoker.commands;

public class ScriptManager {
        private File script;
        private CollectionManager manager;

        public ScriptManager(File script, CollectionManager manager) {
            System.out.println("Выполнение скрипта");
            this.script = script;
            this.manager = manager;
        }
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
        private String[] scriptToTokens() {
            char[] inputChar = new char[2048];
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


    }
