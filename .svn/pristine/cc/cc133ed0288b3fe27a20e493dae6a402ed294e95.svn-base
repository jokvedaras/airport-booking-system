package com.rowan.airport.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JPanel;

public class SeatIcon extends JPanel implements MouseListener
{
	String actionCommand = "";
	boolean taken;
	
	public SeatIcon()
	{
		setSize(48, 48);
	}
	public SeatIcon(boolean isTaken)
	{
		taken = isTaken;
		setSize(48, 48);
	}
	
	public void SetTaken(boolean isTaken)
	{
		taken = isTaken;
	}
	
	public void paint(Graphics g)
	{
		if(taken)
			g.drawImage(Resources.takenSeat, 0, 0, getWidth(), getHeight(), this);
		else
			g.drawImage(Resources.freeSeat, 0, 0, getWidth(), getHeight(), this);
	}
	public void setActionCommand(String cmd)
	{
		actionCommand = cmd;
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		Action act;
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}
}