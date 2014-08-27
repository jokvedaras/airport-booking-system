package com.rowan.airport.core;
import java.util.TreeMap;

/**
 * Team 7 - Jen & Will
 */
public class City
{
    // --------- Non-Static fields ---------
    private Integer							cityID;
    private String							cityName;
    // --------- Static fields ---------
    private static TreeMap<Integer, City>	IDs	= new TreeMap<Integer, City>();

    // --------- Static Methods ---------
    /**
     * This is used in our constructor only, when a City is created, it throws
     * it into our static TreeMap.
     * 
     * @param city The city to put into our TreeMap.
     */
    private static void setID(City city)
    {
        IDs.put(city.getCityID(), city); // Maybe we dont want to always set ID
    }

    /**
     * Gives us the next ID value, starts at 1.
     * @return The highest ID value in our tree map + 1
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
     * Returns the city with the specific ID.
     * @param id The ID of the City to retrieve from the master list.
     * @return City with specific ID
     */
    public static City getCity(Integer id)
    {
        return IDs.get(id);
    }

    // --------- Constructor ---------
    /**
     * Constructor
     * @param cityID The ID number of the city.
     * @param cityName The name of the city.
     */
    public City(Integer cityID, String cityName)
    {
        if (cityID == null)
        {
            throw new IllegalArgumentException("cityID parameter can not be null in City constructor");
        }
        else
        {
            this.cityID = cityID;
        }
        if (cityName == null)
        {
            throw new IllegalArgumentException("cityName parameter can not be null in City constructor");
        }
        else if (cityName.length() == 0)
        {
            throw new IllegalArgumentException("cityName parameter can not be empty in City constructor");
        }
        else
        {
            this.cityName = cityName;
        }
        City.setID(this); // Adds city to our TreeMap
    }

    // --------- Accessors ---------
    /**
     * Returns the city ID
     * @return City ID
     */
    public Integer getCityID()
    {
        return cityID;
    }

    /**
     * Returns the name of the city
     * @return Name of the city
     */
    public String getCityName()
    {
        return cityName;
    }
}
