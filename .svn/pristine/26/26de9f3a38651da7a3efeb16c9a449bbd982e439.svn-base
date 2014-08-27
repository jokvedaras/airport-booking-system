package com.rowan.airport.core;
/**
 * IDAssignmentException is responsible for constructing Exception which can be
 * thrown during the execution of the Flight Class.
 * @author Team 1,3,8
 * 
 */
@SuppressWarnings("serial")
public class IDAssignmentException extends Exception
{
	private int		key;
	private String	type;

	/**
	 * Constructs the Exception object with the given type and int
	 * representation of the key.
	 * @param type The string representation of the type of Exception thrown.
	 * @param key The int representation of the key which caused the Exception.
	 */
	public IDAssignmentException(String type, int key)
	{
		this.type = type;
		this.key = key;
	}

	/**
	 * Generates a String representation of the Exception thrown.
	 * @return The string representation of the Exception.
	 */
	public String toString()
	{
		if (type.equals("FlightID"))
			return "The key " + key + " is already assigned to another Flight.";
		return "The " + type + ", " + key + ", is invalid.";
	}
}
