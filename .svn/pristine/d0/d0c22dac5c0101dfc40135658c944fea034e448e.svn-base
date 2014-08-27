/**
 * Team 4
 * Brandon Romano
 * Joe Kvedaras
 */
package com.rowan.airport.core;
import java.util.TreeMap;
public class Plane 
{
    //----------Fields----------
	
    private Integer planeID;
    private int numberOfSeats; 
    
    //----------Static fields----------
    
    //Plane ID, linked to the Plane object
    private static TreeMap<Integer,Plane> IDs = new TreeMap<Integer,Plane>(); 
    
    //----------Static methods----------
    
    /**
     * This is used in our constructor only,
     * when a Plane is created, it throws it 
     * into our static TreeMap.
     * 
     * @param plane The plane to put into our TreeMap.
     */
    private static void setID(Plane plane)
    {
        IDs.put(plane.getPlaneID(), plane);
    }
    
    /**
     * Gives us the next ID value, starts at 1.
     * @return our highest ID value in our tree map + 1
     */
    public static Integer getNextID()
    {
    	if(IDs.size() != 0)
    		return IDs.lastKey() + 1;    	
    	else //Starting the count at 1
    		return 1;
    }
    
    /**
     * Returns a Plane object linked to a specific ID
     * @param id the ID of the plane we want to retrieve.
     * @return the plane linked to the ID in the parameter.
     */
    public static Plane getPlane(Integer id)
    {
        return IDs.get(id);
    }
    
    //----------Constructor----------
    // we'll be making a new plane with 
    // Plane plane1 = new Plane(Plane.getNextID(),10), 
    // for a plane with 10 seats
    
    /**
     * Constructor of object Plane.
     * @param planeID the ID of the plane.  This should
     * be handled by information in our file, or the call
     * Plane.getNextID()
     * 
     * @param numberOfSeats The number of seats the plane has
     * this should also be handled by the file.
     */
    public Plane(Integer planeID, int numberOfSeats)
    {
        this.planeID = planeID; 
        this.numberOfSeats = numberOfSeats;
        Plane.setID(this);  //Adds plane to our TreeMap
    }
    
    //----------Accessor methods----------
    
    /**
     * This returns the plane ID of a specific plane
     * 
     * @return the ID of a plane
     */
    public Integer getPlaneID()
    {
        return planeID;
    }

    /**
     * Returns the number of seats in the plane
     * @return the number of seats in the plane.
     */
    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }
}