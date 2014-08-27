package com.rowan.airport.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.rowan.airport.core.Flight;
import com.rowan.airport.ui.PlaneView;

/**
 * Global testing class. This will serve as a staging class for the final product.
 * 
 * @author Aaron N. Rudolph and Salvatore DeSapio
 *
 */
public class GlobalTest_GUI extends JFrame implements ActionListener
{
	PlaneView plane = new PlaneView(this);
	
	public GlobalTest_GUI()
	{
		setLayout(new SpringLayout());
		
		add(plane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		makeFrame();
	}
	
	public static void main(String[] args)
	{
		new GlobalTest_GUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(e.getActionCommand());
		int seatIndex = Integer.parseInt(e.getActionCommand());
		plane.ToggleSelection(seatIndex);
	}
	
    private void makeFrame()
    {
        // the following makes sure that our application exits when
        // the user closes its window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 10, 10, 10));

        // makeMenuBar();
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the left side with combobox and scroll list
        JPanel leftPane = new JPanel();
		
        leftPane.add(plane);
        
        contentPane.add(leftPane, BorderLayout.WEST);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
            toolbar.setLayout(new GridLayout(1, 0));
  
            JButton button = new JButton("list");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                           listf();
                       }
                   });
            toolbar.add(button);
            
            button = new JButton("reserve");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) { 
                           reserve(); 
                       }
                   });
            toolbar.add(button);
    
            button = new JButton("schedule");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) { 
                           schedule(); 
                       }
                   });
            toolbar.add(button);
            
            button = new JButton("cancel");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) { 
                           cancel(); 
                       }
                   });
            toolbar.add(button);

            button = new JButton("help");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) { 
                           help(); 
                       }
                   });
            toolbar.add(button);
            
            button = new JButton("quit");
            button.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) { 
                           quit(); 
                       }
                   });
            toolbar.add(button);
        
        contentPane.add(toolbar, BorderLayout.NORTH);
        
        contentPane.add(new JPanel(), BorderLayout.CENTER);

        // building is done - arrange the components      
        pack();
        
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    
    
    private void listf()
    {
    }
    private void reserve()
    {
    }
    private void schedule()
    {
    }
    private void cancel()
    {
    }
    private void help()
    {
    }
    private void quit()
    {
    }
    
}
