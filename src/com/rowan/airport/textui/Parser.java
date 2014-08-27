package com.rowan.airport.textui;
 
import java.util.Scanner;

/**
 * A class that reads input lines from the user. Input is filtered via
 * getCommand for valid commands.
 * 
 * @author Gavin Davis, Tara Crittenden, Michael Arteaga, Salvatore DeSapio, and
 *         Tom Mellor
 * @version 2012.04.26
 */
public class Parser
{
    // Hold all valid command words.
    private CommandWords commands;
    private Scanner reader;

    public Parser()
    {
        commands = new CommandWords("list", "reserve", "schedule", "cancel", "help", "quit");
        reader = new Scanner(System.in);
    }

    /**
     * Read the next command from the user. The returned command will be valid.
     * @return A valid command.
     */
    protected String getCommand()
    {
        String command = null;
        do
        {
            // Print a prompt.
            System.out.print("> ");
            String word = reader.next().toLowerCase();
            // Discard the rest of the line.
            readLine();
            if (commands.isCommand(word))
            {
                command = word;
            }
            else
            {
                System.out.println("Unrecognized command: " + word + "\nValid commands are: " + commands.commandsToString());
            }
        }
        while (command == null);
        return command;
    }

    /**
     * Print out a list of valid command words.
     */
    protected void printCommands()
    {
        System.out.println(commands.commandsToString());
    }

    /**
     * @return A line of text from the user.
     */
    protected String readLine()
    {
        return reader.nextLine();
    }
    
    /**
     * @return A line as an int.
     */
    protected Integer nextInt()
    {
        return reader.nextInt();
    }
}