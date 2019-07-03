package client.trafficLights;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

class SwitchListener implements ActionListener
{
	LightsPanel panel;
	LightsModel model;
	
	public SwitchListener(LightsPanel panel, LightsModel model)
	{
		this.panel = panel;
		this.model = model;
	}
	   	@Override
	   	public void actionPerformed(ActionEvent arg0) 
	   	{
	   		new Thread()
			{
				@Override
				public void run()
				{
					model.setGoOrStop(panel);
				}
			}.start();
	   		
	   	}
}
