package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Player;
import view.ViewModel;

// explicitly declare the function because
// if the wrong function is inputted, no model update
public class DialogController
{
	// if the wrong function is inputted, no model update
	public DialogController(ViewModel model, Player plyr, PlayerFunction addOrRemove)
	{
		switch (addOrRemove)
		{
			case ADD : 		model.addPlayer(plyr); 			break;
			case REMOVE : 	model.removePlayer(plyr);		break;
		}
	}
	
	public DialogController(ViewModel model, int betValue, PlayerFunction bet)
	{
		switch (bet)
		{
			case BET : 		model.placeBet(betValue);				break;
		}
	}
	
}
