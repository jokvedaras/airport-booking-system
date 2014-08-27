package com.rowan.airport.core;
import java.util.GregorianCalendar;

public class Flight_Test
{
	public static void main(String[] args)
	{
		Flight f1, f2, f3, f4, f5, f6;
		Plane p1 = new Plane(1, 10);
		try
		{
			f1 = new Flight(1, 1, 2, new GregorianCalendar(2012, 8, 9, 12, 10, 0), new GregorianCalendar(2012, 8, 9, 12, 12, 0));
			System.out.println(f1);
			f1.addSeats(10, 1, 3, 5, 7);
			System.out.println(f1);
			f1.addNextAvailable(12, 100);
			System.out.println(f1);
		}
		catch (AssignmentException e1)
		{
			System.out.println(e1);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e);
		}
		catch (ConstructionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
