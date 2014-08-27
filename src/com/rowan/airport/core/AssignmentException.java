package com.rowan.airport.core;
/**
 * IDAssignmentException is responsible for constructing Exception which can be
 * thrown during the execution of the Flight Class.
 * @author Team 1,3,8
 * 
 */
@SuppressWarnings("serial")
public class AssignmentException extends Exception
{
	private int		key;
	private String	type;
	boolean			noKey;

	/**
	 * Constructs the Exception object with the given type and int
	 * representation of the key.
	 * @param type The string representation of the type of Exception thrown.
	 * @param key The int representation of the key which caused the Exception.
	 */
	public AssignmentException(String type, int key)
	{
		this.type = type;
		this.key = key;
		noKey = false;
	}

	/**
	 * Constructs the Exception object with the given type and int
	 * representation of the key.
	 * @param type The string representation of the type of Exception thrown.
	 * @param key The int representation of the key which caused the Exception.
	 */
	public AssignmentException(String type)
	{
		this.type = type;
		noKey = true;
	}

	/**
	 * Generates a String representation of the Exception thrown.
	 * @return The string representation of the Exception.
	 */
	public String toString()
	{
		if (!noKey)
		{
			if (type.equals("FlightID"))
				return "The key " + key + " is already assigned to another Flight.";
			return "The " + type + ", " + key + ", is invalid.";
		}
		else
			return type;
	}
}
