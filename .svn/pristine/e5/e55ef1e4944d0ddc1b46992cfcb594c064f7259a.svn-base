package com.rowan.airport.core;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The Reservation Class Constructor requires: int flightID, in customerID, a
 * list of seatNumbers, and a boolean isConsecutive The Reservation Class
 * generates a reservationID and deals with total calculated price for
 * reservation. Allows a mutator method to set cost per ticket (initially set to
 * 100.00)
 * 
 * @author (Sara Schlosser)
 * @author (Jon Gonzoph)
 * @version (April 18, 2012 v1) original
 * @version (updated April 19th, 2012 v2)add, remove, view seats
 * @version (updated April 20th, 2012 v3) with error checking
 */
public class Reservation
{
	// **************Fields**************//
	public final int								flightID;
	public final int								customerID;
	public final List<Integer>						seatNumbers;
	public List<Integer>							seatNumber;
	private Integer									reservationID;
	private double									totalPrice;
	private static double							costPerTicket	= 100.00;
	private static TreeMap<Integer, Reservation>	IDs				= new TreeMap<Integer, Reservation>();

	// **********Static Methods**********//
	/**
	 * Method provides the next id integer If there is no pre-existing key, id
	 * number will begin with 1
	 * @returns Interger id for the next int in TreeMap
	 */
	public static Integer getNextID()
	{
		if (IDs.size() != 0)
			return IDs.lastKey() + 1;
		else
			// Starting the count at 1
			return 1;
	}

	/**
	 * Reservation is added into static TreeMap when a Reservation is created
	 * @param reservation type Reservation
	 */
	public static void setID(Reservation reservation)
	{
		IDs.put(reservation.getReservationID(), reservation);
	}

	/**
	 * @param id type Integer of the reservation
	 * @returns the reservation information attached to specified reservationID
	 */
	public static Reservation getReservation(Integer id)
	{
		return IDs.get(id);
	}

	// ************Constructor************//
	/**
	 * Constructor for objects of class Reservation
	 * @param int flightID, int customerID, List seatNumbers, boolean
	 *        isConsecutive
	 */
	public Reservation(Integer reservationID, int flightID, int customerID)
	{
		if (reservationID == null || reservationID <= 0)
		{
			throw new IllegalArgumentException("ReservationID cannot be null or 0");
		}
		this.reservationID = reservationID;
		if (flightID <= 0)
		{
			throw new IllegalArgumentException("FlightID cannot be null or 0");
		}
		this.flightID = flightID;
		if (customerID <= 0)
		{
			throw new IllegalArgumentException("CustomerID cannot be null or 0");
		}
		this.customerID = customerID;
		this.seatNumbers = new ArrayList<Integer>();
		Reservation.setID(this);
		// this.seatNumber = new ArrayList<Integer>();
	}

	// **********Mutator Methods**********//
	/**
	 * Reservation is added into static TreeMap when a Reservation is created
	 * @param reservation type Reservation
	 */
	public void setCostPerTicket(double cost)
	{
		if (cost <= 0)
		{
			throw new IllegalArgumentException("The CostPerTicket cannot be less then 0.00");
		}
		costPerTicket = cost;
	}

	// **********Accessor Methods**********//
	/**
	 * @return cost per ticket
	 */
	public double getCostPerTicket()
	{
		return costPerTicket;
	}

	/**
	 * @return total price previously calculated
	 */
	public double getTotalPrice()
	{
		return totalPrice;
	}

	/**
	 * Method returns the Reservation ID of specified reservation
	 * @returns reservationID
	 */
	public Integer getReservationID()
	{
		return reservationID;
	}

	/**
	 * Method returns list of seatNumbers
	 * @returns seatNumbers
	 */
	public List<Integer> getSeatNumbers()
	{
		return seatNumbers;
	}

	// **********Reservation Class Methods**********//
	/**
	 * add an individual seat number or a list of seats separated by commas to
	 * the seatNumber list
	 * @param seat number or seat numbers separated by commas
	 */
	public void addSeats(Integer...seatNumber)
	{
		if (seatNumber.length <= 0)
		{
			throw new IllegalArgumentException("You cannot add a zero number of seats");
		}
		for (Integer seat : seatNumber)
		{
			if (seat <= 0)
			{
				throw new IllegalArgumentException("All seat numbers must be equal to or greater than 1");
			}
			seatNumbers.add(seat);
			calculatePrice(seatNumbers);
		}
	}

	/**
	 * remove an individual seat number or a list of seats separated by commas
	 * from the seatNumber list
	 * @param seat number or seat numbers separated by commas
	 */
	public void removeSeats(Integer...seatNumber)
	{
		if (seatNumber.length <= 0)
		{
			throw new IllegalArgumentException("You cannot remove a number of seats below 0");
		}
		for (Integer seat : seatNumber)
		{
			if (seat <= 0)
			{
				throw new IllegalArgumentException("All numbers must be greater than 0");
			}
			seatNumbers.remove(seat);
			calculatePrice(seatNumbers);
		}
	}

	/**
	 * Calculates price based on total number of seats reserved
	 * @param list of seatNumbers
	 */
	public void calculatePrice(List<Integer> seatNumbers)
	{
		totalPrice = costPerTicket * seatNumbers.size();
	}
}
