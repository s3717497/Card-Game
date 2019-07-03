package client.trafficLights;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import client.bouncingBall.BouncingBall;

public class TrafficLights extends JFrame {

	public static void main(String args[])
	{
		final LightsModel model = new LightsModel();
		SwingUtilities.invokeLater(() -> new TrafficLights(model));
   	}

	
	private LightsModel model;
	public TrafficLights(LightsModel model)
	{
		setLayout(new GridLayout(0,6,10,10));
		setSize(1000, 1000);
		
		this.model = model;
	}
	
	public void populate()
	{
		for (LightsPanel panel : model.getPanels())
			add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
