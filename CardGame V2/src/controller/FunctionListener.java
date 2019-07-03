package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Player;
import view.ViewModel;

public class FunctionListener implements ActionListener{
	
	private ViewModel model;
	private PlayerFunction function;
	private Player player;

	public FunctionListener(ViewModel model, PlayerFunction playerFunction)
	{
		this.model = model;
		function = playerFunction;
	}
	
	public FunctionListener(ViewModel model, Player plyr)
	{
		this.model = model;
		player = plyr;
		function = PlayerFunction.SWITCH;
	}

	// since this is only called by a button
	// unused player functions do not get called
	public void actionPerformed(ActionEvent arg0) 
	{
		switch (function)
		{
			case ADD : 		model.getAddPlayerDialog().display(); 		break;
			case BET : 		model.getBetPlayerDialog().display(); 		break;
			case REMOVE : 	model.getRemovePlayerDialog().display();	break;
			case DEAL : 	model.deal();								break;
			case SWITCH : 	model.switchPlayer(player);		 			break;
		}
	}
}
