package org.example.console;

import org.example.exceptions.NullValueException;

public class CommandContext {
    private final boolean isScriptWorking;
    private final String[] scriptArguments;  // если это команда из скрипта
    private final String argument;           // если это обычная команда из консоли

    public CommandContext(String argument, boolean isScriptWorking, String[] scriptArguments) {
        this.argument = argument;
        this.isScriptWorking = isScriptWorking;
        this.scriptArguments = scriptArguments;
    }

    public boolean isScriptWorking() {
        return isScriptWorking;
    }

    public String[] getScriptArguments() {
        return scriptArguments;
    }

    public String getArgument() throws NullValueException {
        if (argument == null)
            throw new NullValueException(("При вводе данной команды необходимо ввести аргумент"), null);
        return argument;
    }
}




