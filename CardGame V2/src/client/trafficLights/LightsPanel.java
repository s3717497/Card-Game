package client.trafficLights;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.trafficLights.LightsModel.TrafficColor;






public class LightsPanel extends JPanel {
	
	TrafficColor activeColor;
	boolean isGreen;
	private AbstractButton switchButton;
   
	public LightsPanel(LightsModel model)
	{
		switchButton = new JButton("Switch");
		switchButton.addActionListener(new SwitchListener(this, model));
	   
		activeColor = TrafficColor.GREEN;
		add(switchButton);
	}
   
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int diameter = 50;
		//place in middle
		int circX = getWidth()/2 - diameter/2;
		// dimensions based on number of traffic colors
		int circY = getHeight()/(TrafficColor.values().length+1);
		
		g.setColor(activeColor.getColor());
		g.fillOval(circX, circY*activeColor.getPosition(), diameter,diameter);
	}
   
   
   
   
   
   

   public void setActiveColor(TrafficColor color)
	{
		activeColor = color;
		repaint();
	}
	   
}
