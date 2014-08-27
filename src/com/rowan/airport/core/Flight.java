package com.rowan.airport.core;
import java.util.*;

/**
 * The Flight Class is responsible for generating Flight objects for use in the
 * RowanGRRA Reservation program. The Flight Class also generates and maintains
 * a master list of all Flights in the Reservation system.
 * @author Team 1, 3, 8
 */
public class Flight
{
	private int								flightID;
	private int								planeID;
	private int								originID;
	private int								destinationID;
	private boolean							cancelled;
	private GregorianCalendar				departTime;
	private GregorianCalendar				arrivalTime;
	private TreeMap<Integer, Integer>		seats;
	private static TreeMap<Integer, Flight>	IDs	= new TreeMap<Integer, Flight>();

	/**
	 * Constructs a Flight object. Used when adding from a file as flightID will
	 * be known.
	 * @param flightID The flightID of the flight, should be read from file.
	 * @param planeID The planeID of the plane associated with the flight.
	 * @param originID The originID of the location where the flight will take
	 *            off from.
	 * @param destinationID The destinationID of the location where the flight
	 *            is flying.
	 * @param departTime The time at which the flight will be departing.
	 * @param arrivalTime The time at which the flight will be arriving.
	 * @throws AssignmentException Thrown when an illegal value is passed to any
	 *             of the constructor parameters. If flightID is already used or
	 *             is less than 1 an exception is thrown. If any other is
	 *             parameter is less than 1 an exception is thrown.
	 * @throws ConstructionException Thrown when all parameters are valid but
	 *             are not valid together. EX: Setting originID and
	 *             destinationID to the same value.
	 * @throws IllegalArgumentException Thrown when a null parameter is passed.
	 */
	public Flight(int flightID, int planeID, int originID, int destinationID, GregorianCalendar departTime, GregorianCalendar arrivalTime)
		throws AssignmentException, ConstructionException
	{
		if (!IDs.containsKey(flightID))
			this.flightID = flightID;
		else
			throw new AssignmentException("FlightID", flightID);
		if (Plane.getPlane(planeID) == null)
			throw new ConstructionException("The planeID " + planeID + " does not exist.");
		if (planeID > 0)
			this.planeID = planeID;
		else
			throw new AssignmentException("PlaneID", planeID);
		if (City.getCity(originID) == null)
			throw new ConstructionException("The cityID " + originID + " does not exist.");
		if (City.getCity(destinationID) == null)
			throw new ConstructionException("The cityID " + destinationID + " does not exist.");
		if (originID != destinationID)
		{
			if (originID > 0)
				this.originID = originID;
			else
				throw new AssignmentException("originID", originID);
			if (destinationID > 0)
				this.destinationID = destinationID;
			else
				throw new AssignmentException("destinationID", destinationID);
		}
		else
			throw new ConstructionException("originID and destinationID were the same.");
		if (departTime != null && arrivalTime != null)
		{
			if (departTime.compareTo(arrivalTime) >= 0)
				throw new ConstructionException("The departTime occurs later than arrivalTime or departTime and arrivalTime are the same.");
			if (departTime.compareTo(new GregorianCalendar()) <= 0)
				throw new ConstructionException("departTime has already occured or is occuring right now.");
			if (arrivalTime.compareTo(new GregorianCalendar()) <= 0)
				throw new ConstructionException("arrivalTime has already occured or is occuring right now.");
			this.departTime = departTime;
			this.arrivalTime = arrivalTime;
		}
		else
		{
			if (departTime == null)
				throw new IllegalArgumentException("departTime is null.");
			else
				throw new IllegalArgumentException("arrivalTime is null.");
		}
		cancelled = false;
		seats = new TreeMap<Integer, Integer>();
		for (Flight flight : IDs.values())
		{
			if (flight.getPlaneID() == planeID
				&& (!flight.getStatus() && !cancelled)
				&& ((departTime.compareTo(flight.getDepartTime()) >= 0 && departTime.compareTo(flight.getArrivalTime()) <= 0)
					|| (arrivalTime.compareTo(flight.getDepartTime()) >= 0 && arrivalTime.compareTo(flight.getArrivalTime()) <= 0) || flight
					.getDepartTime().compareTo(departTime) >= 0 && flight.getArrivalTime().compareTo(arrivalTime) <= 0))
				throw new ConstructionException("Flight " + flightID + " overlaps with flight " + flight.getFlightID() + " on plane " + flight.getPlaneID());
		}
		setID(this);
	}

	/**
	 * Constructs a Flight object. Used when not adding from a file as flightID
	 * will not be known a flightID will be generated and assigned during object
	 * creation.
	 * @param planeID The planeID of the plane associated with the flight.
	 * @param originID The originID of the location where the flight will take
	 *            off from.
	 * @param destinationID The destinationID of the location where the flight
	 *            is flying.
	 * @param departTime The Time at which the flight will be departing.
	 * @param arrivalTime The time at which the flight will be arriving.
	 * @throws AssignmentException Thrown when an illegal value is passed to any
	 *             of the constructor parameters. If flightID is already used or
	 *             is less than 1 an exception is thrown. If any other is
	 *             parameter is less than 1 an exception is thrown.
	 * @throws ConstructionException Thrown when all parameters are valid but
	 *             are not valid together. EX: Setting originID and
	 *             destinationID to the same value.
	 * @throws IllegalArgumentException Thrown when a null parameter is passed.
	 */
	public Flight(int planeID, int originID, int destinationID, GregorianCalendar departTime, GregorianCalendar arrivalTime) throws AssignmentException,
		ConstructionException
	{
		this.flightID = getNextID();
		if (Plane.getPlane(planeID) == null)
			throw new ConstructionException("The planeID " + planeID + " does not exist.");
		if (planeID > 0)
			this.planeID = planeID;
		else
			throw new AssignmentException("PlaneID", planeID);
		if (City.getCity(originID) == null)
			throw new ConstructionException("The cityID " + originID + " does not exist.");
		if (City.getCity(destinationID) == null)
			throw new ConstructionException("The cityID " + destinationID + " does not exist.");
		if (originID != destinationID)
		{
			if (originID > 0)
				this.originID = originID;
			else
				throw new AssignmentException("originID", originID);
			if (destinationID > 0)
				this.destinationID = destinationID;
			else
				throw new AssignmentException("destinationID", destinationID);
		}
		else
			throw new ConstructionException("originID and destinationID were the same.");
		if (departTime != null && arrivalTime != null)
		{
			if (departTime.compareTo(arrivalTime) >= 0)
				throw new ConstructionException("The departTime occurs later than arrivalTime or departTime and arrivalTime are the same.");
			if (departTime.compareTo(new GregorianCalendar()) < 0)
				throw new ConstructionException("departTime has already occured.");
			if (arrivalTime.compareTo(new GregorianCalendar()) < 0)
				throw new ConstructionException("arrivalTime has already occured.");
			this.departTime = departTime;
			this.arrivalTime = arrivalTime;
		}
		else
		{
			if (departTime == null)
				throw new IllegalArgumentException("departTime is null.");
			else
				throw new IllegalArgumentException("arrivalTime is null.");
		}
		cancelled = false;
		seats = new TreeMap<Integer, Integer>();
		for (Flight flight : IDs.values())
		{
			if (flight.getPlaneID() == planeID
				&& (!flight.getStatus() && !cancelled)
				&& ((departTime.compareTo(flight.getDepartTime()) >= 0 && departTime.compareTo(flight.getArrivalTime()) <= 0)
					|| (arrivalTime.compareTo(flight.getDepartTime()) >= 0 && arrivalTime.compareTo(flight.getArrivalTime()) <= 0) || flight
					.getDepartTime().compareTo(departTime) >= 0 && flight.getArrivalTime().compareTo(arrivalTime) <= 0))
				throw new ConstructionException("Flight " + flightID + " overlaps with flight " + flight.getFlightID() + " on plane " + flight.getPlaneID());
		}
		setID(this);
	}

	/**
	 * Adds the Flight to the master public static list of flights and assigns
	 * the next available flightID to the flight.
	 * @param flight The Flight object to add to the master list.
	 * @throws AssignmentException Thrown when an attempted is made to add a
	 *             flight Id already on the master list.
	 */
	public static void setID(Flight flight) throws AssignmentException
	{
		if (IDs.containsKey(flight.getFlightID()))
			throw new AssignmentException("flightID", flight.getFlightID());
		IDs.put(flight.getFlightID(), flight);
	}

	/**
	 * Generates the next available fightID based off the current size of the
	 * master list.
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
	 * Gets the Flight object associated with the given flightID.
	 * @param id The id used to find a Flight object in the master flight list.
	 * @return The Flight object associated with the flightID.
	 */
	public static Flight getFlight(int id)
	{
		if (id <= 0)
			throw new IllegalArgumentException("The ID: " + id + " is invalid.");
		return IDs.get(id);
	}

	/**
	 * Gets the flightID of the current Flight object.
	 * @return The flightID of the current Flight object.
	 */
	public int getFlightID()
	{
		return flightID;
	}

	/**
	 * Gets the planeID of the plane associated with the current Flight object.
	 * @return The planeID of the plane associated with the current Flight
	 *         object.
	 */
	public int getPlaneID()
	{
		return planeID;
	}

	/**
	 * Sets the planeID of the plane associated with the current Flight.
	 * @param planeID The planeID to set as the planeID of the current Flight.
	 * @throws AssignmentException Thrown when an illegal planeID is entered or
	 *             a plane with that ID does not exist.
	 */
	public void setPlaneID(int planeID) throws AssignmentException
	{
		if (Plane.getPlane(planeID) == null)
			throw new AssignmentException("The planeID " + planeID + " does not exist.");
		if (planeID <= 0)
			throw new AssignmentException("planeID", planeID);
		this.planeID = planeID;
	}

	/**
	 * Gets the originID of the origin of the current Flight.
	 * @return The originID of the current Flight.
	 */
	public int getOriginID()
	{
		return originID;
	}

	/**
	 * Sets the originID of the origin associated with the current Flight.
	 * @param originID The originID to set as the originID of the current
	 *            Flight.
	 * @throws AssignmentException
	 */
	public void setOriginID(int originID) throws AssignmentException
	{
		if (City.getCity(originID) == null)
			throw new AssignmentException("The cityID " + originID + " does not exist.");
		if (originID <= 0 || originID == destinationID)
			throw new AssignmentException("originID", originID);
		this.originID = originID;
	}

	/**
	 * Gets the destinationID of the destination of the current Flight.
	 * @return The destinationID of the current Flight.
	 */
	public int getDestinationID()
	{
		return destinationID;
	}

	/**
	 * Sets the destinationID of the destination associated with the current
	 * Flight.
	 * @param destinationID The destinationID to set as the destinationID of the
	 *            current Flight.
	 * @throws AssignmentException
	 */
	public void setDestinationID(int destinationID) throws AssignmentException
	{
		if (City.getCity(destinationID) == null)
			throw new AssignmentException("The cityID " + destinationID + " does not exist.");
		if (destinationID <= 0 || originID == destinationID)
			throw new AssignmentException("destinationID", destinationID);
		this.destinationID = destinationID;
	}

	/**
	 * Gets the departure time of the current Flight object.
	 * @return The departure time of the current flight.
	 */
	public GregorianCalendar getDepartTime()
	{
		return departTime;
	}

	/**
	 * Sets the departure time of the current flight.
	 * @param date The new time to use for the current flights departure time.
	 * @throws IllegalArgumentException Thrown when date is null or date occurs
	 *             after arrivalTime or date is the same as arrivalTime.
	 * @throws AssignmentException Thrown when the departTime occurs after the
	 *             arrivalTime.
	 */
	public void setDepartTime(GregorianCalendar date) throws AssignmentException
	{
		if (date == null)
			throw new IllegalArgumentException("departTime was null");
		if (date.compareTo(getArrivalTime()) >= 0)
			throw new AssignmentException("departTime was the same as arrivalTime or departTime occurs after arrivalTime.");
		for (Flight flight : IDs.values())
		{
			if (!flight.equals(this))
				if (flight.getPlaneID() == planeID
				&& (!flight.getStatus() && !cancelled)
				&& ((departTime.compareTo(flight.getDepartTime()) >= 0 && departTime.compareTo(flight.getArrivalTime()) <= 0)
					|| (arrivalTime.compareTo(flight.getDepartTime()) >= 0 && arrivalTime.compareTo(flight.getArrivalTime()) <= 0) || flight
					.getDepartTime().compareTo(departTime) >= 0 && flight.getArrivalTime().compareTo(arrivalTime) <= 0))
					throw new AssignmentException("Flight " + flightID + " overlaps with flight " + flight.getFlightID() + " on plane " + flight.getPlaneID());
		}
		departTime = date;
	}

	/**
	 * Gets the arrival time of the current Flight object.
	 * @return The arrival time of the current flight.
	 */
	public GregorianCalendar getArrivalTime()
	{
		return arrivalTime;
	}

	/**
	 * Sets the arrival time of the current flight.
	 * @param date The new time to use for the current flights arrival time.
	 * @throws IllegalArgumentException Thrown when date is null or date occurs
	 *             before departTime or date is the same as departTime.
	 */
	public void setArrivalTime(GregorianCalendar date) throws AssignmentException
	{
		if (date == null)
			throw new IllegalArgumentException("arrivalTime was null");
		if (date.compareTo(getDepartTime()) <= 0)
			throw new AssignmentException("arrivalTime was the same as departTime or arrivalTime occurs before departTime.");
		for (Flight flight : IDs.values())
		{
			if (!flight.equals(this))
				if (flight.getPlaneID() == planeID
					&& (!flight.getStatus() && !cancelled)
					&& ((departTime.compareTo(flight.getDepartTime()) >= 0 && departTime.compareTo(flight.getArrivalTime()) <= 0)
						|| (arrivalTime.compareTo(flight.getDepartTime()) >= 0 && arrivalTime.compareTo(flight.getArrivalTime()) <= 0) || flight
						.getDepartTime().compareTo(departTime) >= 0 && flight.getArrivalTime().compareTo(arrivalTime) <= 0))
					throw new AssignmentException("Flight " + flightID + " overlaps with flight " + flight.getFlightID() + " on plane " + flight.getPlaneID());
		}
		arrivalTime = date;
	}

	/**
	 * Gets a HashMap of the seats on the current flight.
	 * @return The HashMap of seats on the current flight.
	 */
	public TreeMap<Integer, Integer> getSeats()
	{
		return seats;
	}

	/**
	 * Adds seats to the current plane. All seats sent will be assigned to the
	 * reservation id given.
	 * @param reservation The reservationID to assign all seats to.
	 * @param seats The seats to add to the flight.
	 * @throws IllegalArgumentException Thrown if a given seat has already been
	 *             assigned a reservation.
	 * @throws AssignmentException
	 */
	public boolean addSeats(int reservation, int...seats) throws IllegalArgumentException, AssignmentException
	{
		if (seats.length > (Plane.getPlane(planeID).getNumberOfSeats() - this.seats.size()))
			throw new AssignmentException("More seats requested than are available.\nSeats Requested: " + this.seats.size() + "\nSeats Available: "
				+ Plane.getPlane(planeID).getNumberOfSeats());
		if (this.seats.size() >= Plane.getPlane(planeID).getNumberOfSeats())
			throw new AssignmentException("The Plane, " + planeID + ", associated with Flight, " + flightID + ", has 0 of "
				+ Plane.getPlane(planeID).getNumberOfSeats() + " seats available.");
		for (int seat : seats)
		{
			if (this.seats.containsKey(seat) || seat < 0)
				return false;
			this.seats.put(seat, reservation);
		}
		return true;
	}

	/**
	 * Adds the requested number of seats to the flight assigning to any
	 * available seat.
	 * @param reservation The reservationID to assign all seats to.
	 * @param numOfSeat The number of seats you wish to add.
	 * @return An int[] array of size 1, with contents -1 at index 0 if the
	 *         number of requested seats could not be added. When seats are
	 *         found the seat numbers assigned to the reservation are returned.
	 */
	public int[] addNextAvailable(int reservation, int numOfSeat)
	{
		int[] fail = new int[1];
		fail[0] = -1;
		if ((numOfSeat > (Plane.getPlane(planeID).getNumberOfSeats() - this.seats.size())) || (this.seats.size() >= Plane.getPlane(planeID).getNumberOfSeats()))
			return fail;
		int[] seatList = new int[numOfSeat];
		int curSeat = 0;
		for (int checkSeat = 0; checkSeat < Plane.getPlane(planeID).getNumberOfSeats(); checkSeat++)
		{
			if (!this.seats.containsKey(checkSeat))
			{
				seatList[curSeat] = checkSeat;
				curSeat++;
			}
			if (curSeat >= numOfSeat)
			{
				for (int seat = 0; seat < seatList.length; seat++)
					seats.put(seatList[seat], reservation);
				return seatList;
			}
		}
		return fail;
	}

	/**
	 * Adds seats to the current Flight's plane if the number of seats requested
	 * are available consecutively.
	 * @param reservation The reservationID to assign all seats to.
	 * @param numOfSeat The number of seats you wish to add consecutively.
	 * @return An int[] array of size 1, with contents -1 at index 0 if the
	 *         number of requested seats could not be found consecutively. When
	 *         seats are found the seat numbers assigned to the reservation are
	 *         returned.
	 */
	public int[] addConsecSeats(int reservation, int numOfSeat)
	{
		int consecSeatCount = 0;
		int[] fail = new int[1];
		fail[0] = -1;
		if ((numOfSeat > (Plane.getPlane(planeID).getNumberOfSeats() - this.seats.size())) || (this.seats.size() >= Plane.getPlane(planeID).getNumberOfSeats()))
			return fail;
		int[] seatList = new int[numOfSeat];
		for (int checkSeat = 0; checkSeat < Plane.getPlane(planeID).getNumberOfSeats(); checkSeat++)
		{
			if (!this.seats.containsKey(checkSeat))
				consecSeatCount++;
			else
				consecSeatCount = 0;
			if (consecSeatCount >= numOfSeat)
			{
				int i = 0;
				for (int addSeat = checkSeat; addSeat > checkSeat - numOfSeat; addSeat--)
				{
					this.seats.put(addSeat, reservation);
					seatList[i] = addSeat;
					i++;
				}
				return seatList;
			}
		}
		return fail;
	}

	/**
	 * Removes an entire reservation from the seats list.
	 * @param reservation The reservation you wish to remove.
	 */
	public boolean removeReservation(int reservation)
	{
		if (seats.containsValue(reservation))
		{
			for (int seat : this.seats.keySet())
			{
				if (seats.get(seat) == reservation)
					seats.remove(seat);
			}
			return true;
		}
		else
			return false;
	}

	/**
	 * Returns a TreeMap of all seats associated with the given reservationID.
	 * @param reservationID The reservationID to look for.
	 * @return A TreeMap of all seats associated with the given reservationID.
	 */
	//Method for GUI, to use this do, Flight.getFlight(id of the flight you want).reservedSeats(id of the reservation you want);
	public TreeMap<Integer, Integer> reserveredSeats(int reservationID)
	{
		TreeMap<Integer, Integer> reservedSeats = new TreeMap<Integer, Integer>();
		for (Integer seat : seats.keySet())
		{
			if (seats.get(seat) == reservationID)
				reservedSeats.put(seat, reservationID);
		}
		return seats;
	}

	/**
	 * Gets the status of the current flight.
	 * @return True when a flight is cancelled. False when a flight is NOT
	 *         cancelled.
	 */
	public boolean getStatus()
	{
		return cancelled;
	}

	/**
	 * Sets the status of the current flight.
	 * @param status The status to set the flight to. A False value means the
	 *            flight is NOT cancelled. A True value means the flight is
	 *            cancelled.
	 */
	public void setStatus(boolean status)
	{
		cancelled = status;
	}

	/**
	 * Creates a String representation of the current Flight object.
	 * @return The String representation of the current Flight object.
	 */
	public String toString()
	{
		String tStr = "Destination: " + getDestinationID();
		tStr += "\n" + "Origin: " + getOriginID();
		tStr += "\n" + "Plane ID: " + getPlaneID();
		tStr += "\n" + "Flight ID: " + getFlightID();
		tStr += "\n" + "Seats: " + getSeats();
		tStr += "\n" + "Depart: " + getDepartTime().getTime().toString();
		tStr += "\n" + "Arrival: " + getArrivalTime().getTime().toString();
		tStr += "\n\n";
		return tStr;
	}
}
// /**
// * Removes any seat from the HashMap seats based off given seat values.
// * Allows for removal of partial reservations.
// * @param seat Any seat you wish to remove.
// */
// public boolean removeSeats(int...seats)
// {
// for (int seat : seats)
// {
// if (seat < 0)
// return false;
// this.seats.remove(seat);
// }
// return true;
// }
// /**
// * Sets the seats of the current flight.
// * @param seats The HashMap of seats to replace with the old seats with.
// */
// public void setSeats(TreeMap<Integer, Integer> seats)
// {
// if (seats == null)
// throw new IllegalArgumentException("seats was null.");
// this.seats = seats;
// }
