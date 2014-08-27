package com.rowan.airport.textui;
import java.io.*;
import java.util.*;
import com.rowan.airport.core.City;
import com.rowan.airport.core.ConstructionException;
import com.rowan.airport.core.Customer;
import com.rowan.airport.core.Flight;
import com.rowan.airport.core.AssignmentException;
import com.rowan.airport.core.Plane;
import com.rowan.airport.core.Reservation2;

/**
 * Provide a textual interface for the Reservation System. Different commands
 * provide access and changes to the data in the system. Prototype to the GUI.
 * 
 * 
 * @author Gavin Davis, Tara Crittenden, Michael Arteaga, and Salvatore DeSapio
 * @version 2012.04.26
 */
public class TextInterface
{
	// A parser for handling user commands.
	private Parser						parser;
	private static ArrayList<Flight>	IDs	= new ArrayList<Flight>();
	private Customer					curCust;

	public static void main(String[] args)
	{
		new TextInterface();
	}
	/**
	 * Initializes the text interface. Loads a predetermined file and logs in the
	 * user.
	 * 
	 * @param book The address book to be manipulated.
	 */
	public TextInterface()
	{
		parser = new Parser();
		loadFile();
		System.out.println("Welcome to GRRA! We speacialize in making sure our customers are sastified.\nDo you have an account with us already?\n\tyes\tno");
		String answer = parser.readLine();
		if (answer.equalsIgnoreCase("yes"))
			login();
		else
			newUser();
		run();
	}

	/**
	 * Logins in a past user.
	 */
	private void login()
	{
		System.out.println("Please type your Customer ID.");
		Integer custID = parser.nextInt();
		
		if (Customer.getCustomer(custID) == null)
		{
			System.out.println("It seems we do not have a record for you please create one now.");
			newUser();
			return;
		}
		curCust = Customer.getCustomer(custID);
		System.out.println("Welcome back " + curCust.getName() + "!");
	}

	/**
	 * If the user is a new customer, this method will create a new instance of
	 * a customer and assign the new user a user ID to be used later if needed.
	 */
	private void newUser()
	{

		System.out.println("Please create an account with us today by entering your name.\nThen press enter and type your current address.");
		String name = parser.readLine();
		String address = parser.readLine();
		curCust = new Customer(name,address);
		System.out.println("Thank you! You are now added to our database. \nFor future reference, your Customer ID is " + curCust.getCustomerID()
			+ "\nThis allows us to identify you. You might want to record this in a safe place.");
	}

	/**
	 * Read a series of commands from the user to interact with the Reservation
	 * system. Stops when the user types 'quit'.
	 */
	private void run()
	{
		System.out.println("Type 'help' for a list of commands.");
		String command;
		do
		{
			command = parser.getCommand();
			if (command.equalsIgnoreCase("list"))
				list();
			else if (command.equalsIgnoreCase("reserve"))
				reserve();
			else if (command.equalsIgnoreCase("schedule"))
				schedule();
			else if (command.equalsIgnoreCase("cancel"))
				cancel();
			else if (command.equalsIgnoreCase("help"))
				help();
			else
			{
				// Do nothing.
			}
		}
		while (!(command.equalsIgnoreCase("quit")));
		System.out.println("Thank you for using our Reservation System. We hope you have a wonderful day!");
	}

	/**
	 * Lists the flight IDs.
	 */
	private void list()
	{
		System.out.println("List of flight IDs: ");
		for (Flight curFlight : IDs)
		{
			System.out.println(curFlight.getFlightID());
		}
	}

	/**
	 * Reserves a seat or seats in a given flight.
	 */
	private void reserve()
	{
		System.out.println("What flight would you like to reserve today?");
		Integer flightID = parser.nextInt();
		System.out.println("flight: " + flightID);
		System.out.println("How many seats will you be needing?");
		Integer seats = parser.nextInt();
		System.out.println("Seats: " + seats);
		boolean cons = false;
		if (seats > 1)
		{
			System.out.println("Would you like those seats to be consective?");
			String boo = parser.readLine();
			boo = parser.readLine();
			if (boo.equalsIgnoreCase("yes"))
				cons = true;
		}
		try
		{
			Reservation2 resFlight = new Reservation2(flightID.intValue(), curCust.getCustomerID(), cons, seats);
			System.out.println("it worked.");
		}
		catch (AssignmentException e)
		{
			e.printStackTrace();
		}
		catch (ConstructionException e)
		{
			e.printStackTrace();
		}
		System.out.println("it worked.");
	}

	/**
	 * Lists all of the info on the current flights like departure, arrival,
	 * etc..
	 */
	private void schedule()
	{
		System.out.println("Information of all Flights: ");
		for (Flight curFlight : IDs)
		{
			System.out.println(curFlight.toString());
		}
	}

	/**
	 * Cancels a reserved flight.
	 */
	private void cancel()
	{
		System.out.println("These are your current reservations: ");
		for(Reservation2 res : Reservation2.reservationsByCust(curCust.getCustomerID()))
			System.out.print(res.getReservationID() + " ");
		System.out.println();
		System.out.println("What reservation would you like to cancel?");
	}

	/**
	 * List the available commands.
	 */
	private void help()
	{
		parser.printCommands();
	}

	/**
	 * This method loads data from a file into the system. Will be moving to a
	 * class soon to provide better access to all without modifying code.
	 */
	public static void loadFile()
	{
		File file = new File("C:\\database.txt");
		try
		{
			Scanner scanner = new Scanner(new FileReader(file));
			try
			{
				// first use a Scanner to get each line
				while (scanner.hasNextLine())
					processLine(scanner.nextLine());
			}
			finally
			{
				// ensure the underlying stream is always closed
				// this only has any effect if the item passed to the Scanner
				// constructor implements Closeable (which it does in this
				// case).
				scanner.close();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	protected static void processLine(String aLine)
	{
		String type = aLine.split("=")[0];
		String data = aLine.split("=")[1];
		System.out.println("Type = " + type + ", Data = " + data);
		Scanner scanner;
		if (type.equalsIgnoreCase("City"))
		{
			// City=1,Glassboro
			scanner = new Scanner(data);
			scanner.useDelimiter(",");
			if (scanner.hasNext())
			{
				String ID = scanner.next();
				String Name = scanner.next();
				City city = new City(Integer.parseInt(ID), Name);
				// Testing
				System.out.println("City: ID = " + ID + ", Name = " + Name);
			}
			else
			{
				// Empty line error
				System.out.println("Empty line");
			}
		}
		else if (type.equalsIgnoreCase("Plane"))
		{
			// Plane=1,20
			scanner = new Scanner(data);
			scanner.useDelimiter(",");
			if (scanner.hasNext())
			{
				String ID = scanner.next();
				String NumberOfSeats = scanner.next();
				Plane plane = new Plane(Integer.parseInt(ID), Integer.parseInt(NumberOfSeats));
				// Testing
				System.out.println("Plane: ID = " + ID + ", NumberOfSeats = " + NumberOfSeats);
			}
		}
		else if (type.equalsIgnoreCase("Customer"))
		{
			// Customer=1,Name, Address
			scanner = new Scanner(data);
			scanner.useDelimiter(",");
			if (scanner.hasNext())
			{
				String ID = scanner.next();
				String Name = scanner.next();
				String address = scanner.next();
				Customer customer = new Customer(Integer.parseInt(ID), Name, address);
				// Testing
				System.out.println("Customer: ID = " + ID + ", Name = " + Name + ", Address = " + address);
			}
		}
		else if (type.equalsIgnoreCase("Flight"))
		{
			// Flight=1,planeID, originID, destinationID, departTime,
			// arrivalTime
			scanner = new Scanner(data);
			scanner.useDelimiter(",");
			if (scanner.hasNext())
			{
				String ID = scanner.next();
				String planeID = scanner.next();
				String originID = scanner.next();
				String destinationID = scanner.next();
				String departTime = scanner.next();
				String arrivalTime = scanner.next();
				// Date parsing
				int departYear = Integer.parseInt(departTime.substring(departTime.lastIndexOf("/") + 1, departTime.indexOf(" ", departTime.lastIndexOf("/"))));
				int departMonth = Integer.parseInt(departTime.substring(0, departTime.indexOf("/")));
				int departDay = Integer.parseInt(departTime.substring(departTime.indexOf("/") + 1, departTime.indexOf("/", departTime.indexOf("/") + 1)));
				int departHour = Integer.parseInt(departTime.substring(departTime.indexOf(" ") + 1, departTime.indexOf(":")));
				int departMin = Integer.parseInt(departTime.substring(departTime.indexOf(":") + 1, departTime.length()));
				int arrivalYear = Integer.parseInt(arrivalTime.substring(arrivalTime.lastIndexOf("/") + 1,
					arrivalTime.indexOf(" ", arrivalTime.lastIndexOf("/"))));
				int arrivalMonth = Integer.parseInt(arrivalTime.substring(0, arrivalTime.indexOf("/")));
				int arrivalDay = Integer.parseInt(arrivalTime.substring(arrivalTime.indexOf("/") + 1, arrivalTime.indexOf("/", arrivalTime.indexOf("/") + 1)));
				int arrivalHour = Integer.parseInt(arrivalTime.substring(arrivalTime.indexOf(" ") + 1, arrivalTime.indexOf(":")));
				int arrivalMin = Integer.parseInt(arrivalTime.substring(arrivalTime.indexOf(":") + 1, arrivalTime.length()));
				System.out.println("Flight: ID = " + ID + ", planeID = " + planeID + ", originID = " + originID + ", destinationID " + destinationID
					+ ", Depart Time = " + departTime + ", Arrival Time = " + arrivalTime);
				try
				{
					Flight flight = new Flight(Integer.parseInt(ID), Integer.parseInt(planeID), Integer.parseInt(originID), Integer.parseInt(destinationID),
						new GregorianCalendar(departYear, departMonth, departDay, departHour, departMin, 00), new GregorianCalendar(arrivalYear, arrivalMonth,
							arrivalDay, arrivalHour, arrivalMin, 00));
					IDs.add(flight);
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
				catch (AssignmentException e)
				{
					e.printStackTrace();
				}
				catch (ConstructionException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				// Empty line error
				System.out.println("Empty line");
			}
		}
		// no need to call scanner.close(), since the source is a String
	}
}