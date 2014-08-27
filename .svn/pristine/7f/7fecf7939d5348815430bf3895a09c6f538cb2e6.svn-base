package com.rowan.airport.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class GUITest extends JFrame implements ActionListener
{
	TreeMap<Integer, Integer> takenSeats = new TreeMap<Integer, Integer>();
	PlaneView pv = new PlaneView(this);
	public GUITest()
	{
		setLayout(new SpringLayout());
		add(pv);
		
		takenSeats.put(0, PlaneView.SEAT_OCCUPIED);
		takenSeats.put(1, PlaneView.SEAT_OCCUPIED);
		takenSeats.put(2, PlaneView.SEAT_OPEN);
		takenSeats.put(3, PlaneView.SEAT_RESERVED);
		takenSeats.put(4, PlaneView.SEAT_OCCUPIED);
		takenSeats.put(5, PlaneView.SEAT_OCCUPIED);
		
		pv.SetSeats(takenSeats);
		
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		new GUITest().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int seatIndex = Integer.parseInt(e.getActionCommand());
		pv.ToggleSelection(seatIndex);
	}
}
