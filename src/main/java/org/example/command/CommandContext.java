package org.example.command;

import org.example.exceptions.NullValueException;

/**
 * Класс CommandContext хранит информацию о контексте выполнения команды.
 * Включает в себя основной аргумент команды, флаг выполнения скрипта и аргументы скрипта.
 */
public class CommandContext {
    /**
     * Флаг, указывающий, выполняется ли команда в режиме скрипта.
     */
    private final boolean isScriptWorking;

    /**
     * Массив аргументов, переданных в скрипт.
     */
    private final String[] scriptArguments;

    /**
     * Основной аргумент команды.
     */
    private final String argument;

    /**
     * Конструктор для создания контекста команды.
     *
     * @param argument        основной аргумент команды.
     * @param isScriptWorking флаг, указывающий, выполняется ли команда в режиме скрипта.
     * @param scriptArguments массив аргументов, переданных в скрипт.
     */
    public CommandContext(String argument, boolean isScriptWorking, String[] scriptArguments) {
        this.argument = argument;
        this.isScriptWorking = isScriptWorking;
        this.scriptArguments = scriptArguments;
    }

    /**
     * Проверяет, выполняется ли команда в режиме скрипта.
     *
     * @return true, если команда выполняется в режиме скрипта, иначе false.
     */
    public boolean isScriptWorking() {
        return isScriptWorking;
    }

    /**
     * Возвращает массив аргументов, переданных в скрипт.
     *
     * @return массив строк с аргументами скрипта.
     */
    public String[] getScriptArguments() {
        return scriptArguments;
    }

    /**
     * Возвращает основной аргумент команды.
     *
     * @return строка с аргументом команды.
     * @throws NullValueException если аргумент отсутствует (null).
     */
    public String getArgument() throws NullValueException {
        if (argument == null)
            throw new NullValueException(("При вводе данной команды необходимо ввести аргумент"), null);
        return argument;
    }
}




