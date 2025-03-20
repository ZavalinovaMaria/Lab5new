package org.example.command.commandList;

import org.example.command.Command;
import org.example.command.CommandContext;
import org.example.collectionInstruments.GroupsCollectionManager;
import org.example.console.ScriptManager;
import org.example.exceptions.NullValueException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand implements Command {

    GroupsCollectionManager collection;
    private final Set<String> scriptHistory = new HashSet<>();

    public ExecuteScriptCommand(GroupsCollectionManager collection) {
        this.collection = collection;
    }
    @Override
    public void execute(CommandContext context){
        String scriptFileName;
        try{
            scriptFileName = context.getArgument();
        }catch (NullValueException e){
        System.out.println(e.getMessage());
        return;
        }
        if (scriptHistory.contains(scriptFileName)) {
            System.out.println("Данный скрипт уже был выполнен"+ scriptFileName);
            return;
        }
        else{
            scriptHistory.add(scriptFileName);
        }
        File scriptFile = new File(scriptFileName);
        if (!scriptFile.exists() || !scriptFile.isFile() ) {
            System.out.println("Файл скрипта не найден: " + scriptFileName);
            return;
        }

        ScriptManager scriptManager = new ScriptManager(scriptFile);

        try {
            scriptManager.executeScript();
            System.out.println(successExecution());
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }


    @Override
    public String description() {
        return "execute_script {file}: считать и исполнить скрипт";
    }
    @Override
    public String successExecution() {
        return "Скрипт выполнен успешно" ;
    }

}
