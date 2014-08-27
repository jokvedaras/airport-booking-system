package com.rowan.airport.core;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Reservation2 is responsible for handling the creation of Reservation objects
 * and determining the validity of a requested Reservation why communicating
 * with the Flight class.
 * @author Sara Schlosser, Jon Gonzoph, Modified by, Adam Wentzel
 * 
 */
public class Reservation2
{
	public final int								flightID;
	public final int								customerID;
	public int[]									listOfSeats;
	private Integer									reservationID;
	private static double							costPerTicket	= 100.00;
	private static TreeMap<Integer, Reservation2>	IDs				= new TreeMap<Integer, Reservation2>();

	/**
	 * Generates the next available reservationID based off the current contents
	 * of the master list.
	 * @return The int value of the next ID.
	 */
	public static int getNextID()
	{
		for (int key = 1; key <= IDs.keySet().size(); key++)
		{
			if (!IDs.containsKey(key))
				return key;
		}
		if (IDs.keySet().size() == 0)
			return 1;
		return IDs.keySet().size() + 1;
	}

	/**
	 * Adds the Reservation to the master public static list of Reservation and
	 * assigns the next available ReservationID to the Reservation.
	 * @param reservation The Reservation object to add to the master list.
	 * @throws IDAssignmentException Thrown when an attempted is made to add a
	 *             ReservationID already on the master list.
	 */
	public static void setID(Reservation2 reservation)
	{
		if (IDs.containsKey(reservation.getReservationID()))
			throw new IllegalArgumentException("The reservationID, " + reservation.getReservationID() + " is already on the list.");
		IDs.put(reservation.getReservationID(), reservation);
	}

	/**
	 * Gets the Reservation object associated with the given reservationID.
	 * @param id The id used to find a Reservation object in the master
	 *            Reservation list.
	 * @return The Reservation object associated with the ReservationID.
	 */
	public static Reservation2 getReservation(Integer id)
	{
		return IDs.get(id);
	}

	/**
	 * Constructs a Reservation object given the required information. Used when
	 * pulling Reservation info from a file and ReservationID is known.
	 * @param reservationID The reservationID to associate with the Reservation.
	 * @param flightID The flightID of the Flight the Reservation is for.
	 * @param customerID The customerID of the customer making the Reservation.
	 * @param conSec True when the customer wants consecutive seats. False when
	 *            seats are selected based on seat numbers.
	 * @param seats When conSec is true, The first number entered for seats is
	 *            assumed to be the # of seats requested and an attempt is made
	 *            to find that many seats consecutively. When conSec is false,
	 *            the numbers entered for seats are assumed to be requested seat
	 *            numbers and an attempt is made to reserve those seats.
	 * @throws AssignmentException Thrown when a seat assignment due to bad seat
	 *             configuration.
	 * @throws IllegalArgumentException Thrown when an illegal parameter is
	 *             sent.
	 * @throws ConstructionException Thrown when the requested seats are all
	 *             valid but not available.
	 */
	public Reservation2(int reservationID, int flightID, int customerID, boolean conSec, int...seats) throws ConstructionException, IllegalArgumentException,
		AssignmentException
	{
		if (IDs.containsKey(reservationID) || reservationID <= 0)
			throw new ConstructionException("reservationID, " + reservationID + " already exists or is less than 1.");
		if (flightID <= 0 || Flight.getFlight(flightID) == null)
			throw new ConstructionException("No flight exists with ID, " + flightID + ".");
		if (customerID <= 0 || Customer.getCustomer(customerID) == null)
			throw new ConstructionException("No customer exists with ID, " + customerID + ".");
		if (seats.length == 0)
			throw new ConstructionException("No seats requested.");
		if (!conSec)
		{
			if (!Flight.getFlight((flightID)).addSeats(reservationID, seats))
				throw new ConstructionException("One of the requested seats is unavailable.");
			listOfSeats = new int[seats.length];
			listOfSeats = seats;
		}
		else
		{
			listOfSeats = new int[seats[0]];
			listOfSeats = Flight.getFlight((flightID)).addConsecSeats(reservationID, seats[0]);
			if (listOfSeats[0] == -1)
				throw new ConstructionException(seats[0] + " consecutive seats are not available.");
		}
		this.customerID = customerID;
		this.flightID = flightID;
		this.reservationID = reservationID;
		setID(this);
	}

	/**
	 * Constructs a Reservation object given the required information. Used when
	 * ReservationID is unknown. A unique ID will be generated upon creation.
	 * @param reservationID The reservationID to associate with the Reservation.
	 * @param flightID The flightID of the Flight the Reservation is for.
	 * @param customerID The customerID of the customer making the Reservation.
	 * @param conSec True when the customer wants consecutive seats. False when
	 *            seats are selected based on seat numbers.
	 * @param seats When conSec is true, The first number entered for seats is
	 *            assumed to be the # of seats requested and an attempt is made
	 *            to find that many seats consecutively. When conSec is false,
	 *            the numbers entered for seats are assumed to be requested seat
	 *            numbers and an attempt is made to reserve those seats.
	 * @throws AssignmentException Thrown when a seat assignment due to bad seat
	 *             configuration.
	 * @throws IllegalArgumentException Thrown when an illegal parameter is
	 *             sent.
	 * @throws ConstructionException Thrown when the requested seats are all
	 *             valid but not available.
	 */
	public Reservation2(int flightID, int customerID, boolean conSec, int...seats) throws IllegalArgumentException, AssignmentException, ConstructionException
	{
		if (flightID <= 0 || Flight.getFlight(flightID) == null)
			throw new IllegalArgumentException("No flight exists with ID, " + flightID + ".");
		if (Customer.getCustomer(customerID) == null)
			throw new IllegalArgumentException("No customer exists with ID, " + customerID + ".");
		if (seats.length == 0)
			throw new IllegalArgumentException("No seats requested.");
		this.reservationID = getNextID();
		if (!conSec)
		{
			if (!Flight.getFlight((flightID)).addSeats(reservationID, seats))
				throw new ConstructionException("One of the requested seats is unavailable.");
			listOfSeats = new int[seats.length];
			listOfSeats = seats;
		}
		else
		{
			listOfSeats = new int[seats[0]];
			listOfSeats = Flight.getFlight((flightID)).addConsecSeats(reservationID, seats[0]);
			if (listOfSeats[0] == -1)
				throw new ConstructionException(seats[0] + " consecutive seats are not available.");
		}
		this.customerID = customerID;
		this.flightID = flightID;
		setID(this);
	}

	/**
	 * Sets the cost per ticket for all flights. Because all flights are
	 * regional all flights will cost the same.
	 * @param cost The cost of a ticket.
	 */
	public static void setCostPerTicket(double cost)
	{
		if (cost <= 0)
		{
			throw new IllegalArgumentException("The CostPerTicket cannot be less then $0.00");
		}
		costPerTicket = (double) (((int) (cost * 100)) / 100);
	}

	/**
	 * Returns the cost per ticket.
	 * @return The cost per ticket.
	 */
	public static double getCostPerTicket()
	{
		return costPerTicket;
	}

	/**
	 * Returns the total price of a reservation based off the number of seats
	 * requested and the cost per ticket.
	 * @return The total price of a reservation.
	 */
	public String getTotalPrice()
	{
		return "$" + new DecimalFormat("#.##").format(((listOfSeats.length * (costPerTicket * 100)) / 100));
	}

	/**
	 * Returns the reservationID of the current Reservation.
	 * @return the reservationID of the current Reservation.
	 */
	public Integer getReservationID()
	{
		return reservationID;
	}

	/**
	 * Removes a reservation from the Flights list of seats and from the master
	 * list of Reservations.
	 */
	public void removeReservation()
	{
		if (Flight.getFlight(flightID).removeReservation(reservationID))
			;
		IDs.remove(this);
	}

	/**
	 * Returns an int array of all seats numbers associated with a reservation.
	 * @return an int array of all seats numbers associated with a reservation.
	 */
	public int[] getSeatNumbers()
	{
		return listOfSeats;
	}

	public static ArrayList<Reservation2> reservationsByCust(int custID)
	{
		ArrayList<Reservation2> reservations = new ArrayList<Reservation2>();
		for (Integer res : IDs.keySet())
		{
			if (getReservation(res).getCustomerID() == custID)
				reservations.add(getReservation(res));
		}
		return reservations;
	}

	/**
	 * Returns the customerID associated with this reservation.
	 * @return the customerID
	 */
	public int getCustomerID()
	{
		return customerID;
	}
}
