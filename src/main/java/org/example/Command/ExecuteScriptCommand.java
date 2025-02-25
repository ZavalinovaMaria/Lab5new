package org.example.Command;

import org.example.console.CollectionManager;

public class ExecuteScriptCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CollectionManager manager;

    public ExecuteScriptCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(){
        manager.executeScript();
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
