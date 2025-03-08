package org.example.Command;

import org.example.console.CommandContext;
import org.example.console.GroupsCollectionManager;
import org.example.console.ScriptManager;
import org.example.exceptions.NullValueException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    GroupsCollectionManager collection;
    private Set<String> scriptHistory = new HashSet<>();

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
            System.out.println("Данный скрипт уже был выполнен");
            return;}
        else{
            scriptHistory.add(scriptFileName);
        }


        File scriptFile = new File(scriptFileName);

        if (!scriptFile.exists() || !scriptFile.isFile() ) {
            System.out.println("Ошибка: файл скрипта не найден: " + scriptFileName);
            return;
        }

        ScriptManager scriptManager = new ScriptManager(scriptFile, collection);

        try {
            scriptManager.executeScript();  // Выполнение скрипта
            System.out.println("Скрипт выполнен успешно.");
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
           // e.printStackTrace();
        }


    }
    /**
     * Method that returns command description
     * @return Command description
     */


    @Override
    public String description() {
        return "execute_script {file}: считать и исполнить скрипт";
    }

}
