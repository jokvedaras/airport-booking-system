package com.rowan.airport.core;
import java.util.TreeMap;

/**
 * Write a description of class Customer here. no.
 * 
 * @author tom and ken
 * @version 4/20/2012
 */
public class Customer
{
	/*--Fields------------------------------------------------------Fields*/
	private String								name;
	private String								address;
	private Integer								customerID;
	private static TreeMap<Integer, Customer>	IDs	= new TreeMap<Integer, Customer>();

	/*--Constructors------------------------------------------Constructors*/
	/**
	 * Constructor for objects of class Customer
	 * @param customerID
	 * @param name
	 * @param address
	 */
	public Customer(Integer customerID, String name, String address) throws IllegalArgumentException
	{
		// checking and setting customerID param
		if (customerID == null)
			throw new IllegalArgumentException("customerID parameter can not be null in Customer constructor");
		else
			this.customerID = customerID;
		// checking and setting name param
		if (name == null)
			throw new IllegalArgumentException("name parameter can not be null in Customer constructor");
		else if (name.length() == 0)
			throw new IllegalArgumentException("name parameter must have non zero length in Customer constructor");
		else
			this.name = name;
		// checking and setting address param
		if (address == null)
			throw new IllegalArgumentException("address parameter can not be null in Customer constructor");
		else if (address.length() == 0)
			throw new IllegalArgumentException("address parameter must have non zero length in Customer constructor");
		else
			this.address = address;
		// adds this customer to static map
		setID(this);
	}
	/**
	 * Constructor for objects of class Customer
	 * @param customerID
	 * @param name
	 * @param address
	 */
	public Customer(String name, String address) throws IllegalArgumentException
	{
		// checking and setting customerID param
		this.customerID = getNextID();
		// checking and setting name param
		if (name == null)
			throw new IllegalArgumentException("name parameter can not be null in Customer constructor");
		else if (name.length() == 0)
		{
			
			System.out.println("Constuctor: " + name);
			throw new IllegalArgumentException("name parameter must have non zero length in Customer constructor");
		}
		else
			this.name = name;
		// checking and setting address param
		if (address == null)
			throw new IllegalArgumentException("address parameter can not be null in Customer constructor");
		else if (address.length() == 0)
			throw new IllegalArgumentException("address parameter must have non zero length in Customer constructor");
		else
			this.address = address;
		// adds this customer to static map
		setID(this);
	}

	/*--Accessors------------------------------------------------Accessors*/
	/**
	 * Retrieves instance name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Retrieves instance address
	 * @return address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Retrieves instance ID
	 * @return ID
	 */
	public Integer getCustomerID()
	{
		return customerID;
	}

	/**
	 * Returns the ID of the next customer
	 * @return nextID
	 */
	public static Integer getNextID()
	{
		if (IDs.size() != 0)
			return IDs.lastKey() + 1;
		else
			return 1;
	}

	/**
	 * Retrieves the customer with given ID
	 * @param ID
	 * @return customer at ID
	 */
	public static Customer getCustomer(Integer ID)
	{
		return IDs.get(ID);
	}

	/**
	 * Generates and returns object state as a String
	 * @return object state
	 */
	public String toString()
	{
		return "CustomerID: " + customerID.intValue() + "\nCustomer Name: " + name + "\nCustomer Address: " + address + "\nCustomer Count: " + IDs.size();
	}

	/*--Mutators--------------------------------------------------Mutators*/
	/**
	 * Changes customer name to given name.
	 * @param name
	 */
	public void setName(String name) throws IllegalArgumentException
	{
		// checks and sets new name
		if (name == null)
			throw new IllegalArgumentException("name parameter can not be null in Customer.setName(String name)");
		else if (name.length() == 0)
			throw new IllegalArgumentException("name parameter must have non zero length in Customer.setName(String name)");
		else
			this.name = name;
	}

	/**
	 * Sets instance address
	 * @param address
	 */
	public void setAddress(String address) throws IllegalArgumentException
	{
		if (address == null)
			throw new IllegalArgumentException("address parameter can not be nullin setAddress(String address)");
		else if (address.length() == 0)
			throw new IllegalArgumentException("address parameter must have non zero length in Customer.setAddress(String Address)");
		else
			this.address = address;
	}

	/**
	 * Adds the given customer object to the static map
	 * @param customer
	 */
	public static void setID(Customer customer) throws IllegalArgumentException
	{
		if (customer == null)
			throw new IllegalArgumentException("customer paramater can not be null in setID(Customer customer)");
		else
			IDs.put(customer.getCustomerID(), customer);
	}
}
