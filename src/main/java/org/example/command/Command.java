package org.example.command;

//написать что то про то что это базовый интерфейс который реализует все классы команд
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
    String successExecution();

}
