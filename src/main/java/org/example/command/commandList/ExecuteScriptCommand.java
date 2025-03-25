package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.utils.GroupsCollectionManager;
import org.example.console.ScriptManager;
import org.example.exceptions.NullValueException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс реализует интерфейс {@link Command} и предназначен для
 * считывания и выполнения скрипта.
 */
public class ExecuteScriptCommand implements Command {

    GroupsCollectionManager collection;
    private final Set<String> scriptHistory = new HashSet<>();

    public ExecuteScriptCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }

    /**
     * Выполняет команду считывания и выполнения скрипта.
     * <p>
     * Процесс выполнения включает следующие шаги:
     * <ol>
     *   <li>Получение имени файла скрипта из контекста команды с помощью {@link CommandContext#getArgument()}.
     *       Если аргумент отсутствует, выводится сообщение об ошибке и выполнение прекращается.</li>
     *   <li>Проверка наличия файла в хранилище выполненных скриптов. Если скрипт уже был исполнен, выводится
     *       соответствующее сообщение, и выполнение команды завершается.</li>
     *   <li>Проверка существования файла и его типа. Если файл не найден или не является файлом, выводится
     *       сообщение об ошибке.</li>
     *   <li>Создание экземпляра {@link ScriptManager} для работы с файлом скрипта и выполнение скрипта.
     *       В случае возникновения исключений при выполнении скрипта, выводится сообщение с описанием ошибки.</li>
     *   <li>При успешном выполнении скрипта выводится сообщение об успешном завершении операции.</li>
     * </ol>
     * </p>
     *
     * @param context контекст выполнения команды, содержащий аргументы и параметры работы.
     */
    @Override
    public void execute(CommandContext context) {
        String scriptFileName;
        try {
            scriptFileName = context.getArgument();
        } catch (NullValueException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (scriptHistory.contains(scriptFileName)) {
            System.out.println("Данный скрипт уже был выполнен" + scriptFileName);
            return;
        } else {
            scriptHistory.add(scriptFileName);
        }
        File scriptFile = new File(scriptFileName);
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Файл скрипта не найден: " + scriptFileName);
            return;
        }

        ScriptManager scriptManager = new ScriptManager(scriptFile);
        try {
            scriptManager.executeScript();
            System.out.println(successExecutionMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }


    @Override
    public String description() {
        return "execute_script {file}: считать и исполнить скрипт";
    }

    @Override
    public String successExecutionMessage() {
        return "Скрипт выполнен успешно";
    }

}
