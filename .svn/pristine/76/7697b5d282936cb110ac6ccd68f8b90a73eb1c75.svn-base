package com.rowan.airport.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A component for displaying the plane and available seats.
 * Any classes which use this must implement ActionListener
 * so that they can receive event notifications.
 * 
 * @author Aaron N. Rudolph
 *
 */

public class PlaneView extends JPanel
{
	public static final int SEAT_OCCUPIED = 0x0;
	public static final int SEAT_RESERVED = 0x1;
	public static final int SEAT_OPEN = 0x2;
	
	private TreeMap<Integer, Integer> seats;
	private GridLayout planeLayout;
	
	private ActionListener receiver;
	
	public PlaneView(ActionListener a)
	{
		receiver = a;
		planeLayout = new GridLayout(1, 1, 5, 5);
		setLayout(planeLayout);
		setBackground(Color.gray);
	}
	
	public void SetSeats(Map<Integer, Integer> takenSeats)
	{
		seats = (TreeMap<Integer, Integer>) takenSeats;
		BuildUI();
	}
	
	private void BuildUI()
	{
		planeLayout.setRows(seats.keySet().size());
		
		removeAll();
		Set<Integer> keySet = seats.keySet();
		
		for(Iterator<Integer> i = keySet.iterator(); i.hasNext();)
		{
			Integer element = i.next();
			JButton icon = new JButton(new ImageIcon(new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)));
			icon.setDisabledIcon(new ImageIcon(Resources.takenSeat));
			if(seats.get(element) == SEAT_RESERVED)
			{
				icon.setIcon(new ImageIcon(Resources.reservedSeat));
			}
			if(seats.get(element) == SEAT_OPEN)
			{
				icon.setIcon(new ImageIcon(Resources.freeSeat));
			}
			icon.setEnabled(seats.get(element) != 0x0);
			icon.setBackground(Color.gray);
			icon.setActionCommand(element.toString());
			icon.addActionListener(receiver);
			add(icon);
		}
		validate();
	}
	
	public void ToggleSelection(int seatIndex)
	{
		if(seats.get(new Integer(seatIndex)) == SEAT_OPEN)
		{
			seats.keySet().remove(seatIndex);//This line does nothing.  seats.keySet() returns a set, .remove(seatIndex) removes a value from the set returned from .keySet()  the modified set is then thrown away as its not saved to a variable
			seats.put(seatIndex, PlaneView.SEAT_RESERVED);
		}
		else if(seats.get(new Integer(seatIndex)) == SEAT_RESERVED)
		{
			seats.keySet().remove(seatIndex);//This line does nothing.  seats.keySet() returns a set, .remove(seatIndex) removes a value from the set returned from .keySet()  the modified set is then thrown away as its not saved to a variable
			seats.put(seatIndex, PlaneView.SEAT_OPEN);
		}
		BuildUI();
	}
}
