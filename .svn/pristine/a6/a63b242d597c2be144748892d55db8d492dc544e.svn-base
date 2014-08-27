package com.rowan.airport.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 * 
 * Contains resources such as icons for running GUI-related stuff.
 * 
 * @author Aaron N. Rudolph
 *
 */

public class Resources
{
	public static Image takenSeat;
	public static Image freeSeat;
	public static Image reservedSeat;
	
	static
	{
		URL freeSeatURL = Resources.class.getResource("/res/seat_free.png");
		freeSeat = Toolkit.getDefaultToolkit().getImage(freeSeatURL);
		URL takenSeatURL = Resources.class.getResource("/res/seat_taken.png");
		takenSeat = Toolkit.getDefaultToolkit().getImage(takenSeatURL);
		URL reservedSeatURL = Resources.class.getResource("/res/seat_reserved.png");
		reservedSeat = Toolkit.getDefaultToolkit().getImage(reservedSeatURL);
	}
}
