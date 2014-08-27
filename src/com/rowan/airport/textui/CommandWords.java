package com.rowan.airport.textui;

 
/**
 * This class holds an enumeration of all command words known to the program.
 * 
 * @author Gavin Davis, Tara Crittenden, Michael Arteaga, Salvatore DeSapio, and
 *         Tom Mellor
 * @version 2012.04.20
 */
public class CommandWords
{
    // an array that holds all valid command words
    private static String   validCommands[];

    /**
     * Constructor for CommandWords
     */
    public CommandWords(String...commands)
    {
        validCommands = commands;
    }

    /**
     * Check whether a given String is a valid command word.
     * @param aString The string to be checked.
     * @return true if it is valid, false if it isn't.
     */
    public boolean isCommand(String userCommand)
    {
        if (userCommand == null)
            throw new IllegalArgumentException("userCommand paramater in CommandWords.isCommand(String userCommand) can not be null");
        else if (userCommand.length() == 0)
            throw new IllegalArgumentException("userCommand paramater must have non zero length in CommandWords.isCommand(String userCommand)");
        else
            for (String test : validCommands)
                if (test.equals(userCommand))
                    return true;
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public String commandsToString()
    {
        String c = "";
        for (String command : validCommands)
            c = c.concat(command + "  ");
        return c;
    }

    /**
     * Returns the array of string commands
     */
    public String[] availableCommands()
    {
        return validCommands;
    }
}