package org.example.Command;

import org.example.console.CommandContext;

public interface Command {
    /**
     * Command execution method
     */
    void execute(CommandContext context);
    /**
     * Method that return description
     * @return Command description
     */
    String description();

}
