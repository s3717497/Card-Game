package controller;

import view.ViewModel;

public enum PlayerFunction 
{
	ADD, BET, REMOVE, SWITCH, DEAL;
	
	public String getName()
	{
		// ADD => Add, etc.
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
	
	public void function()
	{
		switch (this)
		{
		case ADD:
			break;
		case BET:
			break;
		case DEAL:
			break;
		case REMOVE:
			break;
		case SWITCH:
			break;
		default:
			break;
			
		}
	}
}

