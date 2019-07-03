package client.trafficLights;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;


public class LightsModel {

	
	
	private TrafficLights frame;
	private LightsPanel[] panels;
	public LightsModel()
	{
		frame = new TrafficLights(this);
		
		panels = new LightsPanel[6];
		
		for (int i=0; i<panels.length; i++)
		{
			panels[i] = new LightsPanel(this);
		}
		
		frame.populate();
		
	}

	public void setGoOrStop(LightsPanel panel)
	{	
		panel.isGreen = !panel.isGreen;
		panel.setActiveColor(TrafficColor.YELLOW);
		delay();
		panel.setActiveColor(panel.isGreen ? TrafficColor.RED : TrafficColor.GREEN);
	}
	
	
	
	
	
	
	
	
	
	public enum TrafficColor
	{
		GREEN(1), YELLOW(2), RED(3);
		
		int position; 
		
		TrafficColor(int pos)
		{
			position = pos;
		}
		
		public int getPosition()
		{
			return position;
		}
		
		public Color getColor()
		{
			switch (this)
			{
				case GREEN: return Color.GREEN;
				case YELLOW:return Color.YELLOW;
				case RED:	return Color.RED;
			}
			return null;
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void delay() 
	{
		try 
		{
			TimeUnit.MILLISECONDS.sleep(1000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public LightsPanel[] getPanels()
	{
		return panels;
	}
	
	public TrafficLights getFrame()
	{
		return frame;
	}
	
}
