package view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import controller.PlayerFunction;
import controller.SelectorListener;
import controller.FunctionListener;
import model.Player;
import model.interfaces.GameEngine;
import view.ViewModel;
import view.interfaces.ViewCallback;

public class GameBar extends JMenuBar implements ViewCallback{

	private JMenu playerFunctions;
	private JMenu file;
	private ViewModel model;
	private GameEngine engine;
	
	private JMenu switchPlayer;
	private Map<Player, JMenuItem> playerItems;
	
	//does not a "no player" state
	//rather a general "all players" state
	public GameBar(ViewModel model)
	{
		this.model = model;
		engine = model.getGameEngine();
		
		file = new JMenu("File");
		file.add(exitItem());
		
		switchPlayer = new JMenu("Switch Player");
		playerItems = new HashMap<>();
		switchItem();
		
		playerFunctions = new JMenu("Player functions");
		playerFunctions.add(addRemoveItem(PlayerFunction.ADD));
		playerFunctions.add(addRemoveItem(PlayerFunction.REMOVE));
		playerFunctions.add(switchPlayer);
		
		
		add(file);
		add(playerFunctions);
		
		model.addViewCallback(this);
	}
	
	
	private JMenuItem addRemoveItem(PlayerFunction function)
	{
		JMenuItem functionPlayer = new JMenuItem(function.getName() + " Player");
		functionPlayer.addActionListener(new FunctionListener(model, function));
		
		return functionPlayer;
	}
	
	private void switchItem()
	{
		switchPlayer.removeAll();
		
		for (Player plyr : engine.getAllPlayers())
		{
			JMenuItem plyrItem = new JRadioButtonMenuItem(plyr.getName());
			if (model.getCurrentPlayer() != null)
				plyrItem.setSelected(model.getCurrentPlayer().equals(plyr));
			plyrItem.addActionListener(new FunctionListener(model, plyr));
//			if (currentPlayer != null)...
			
			playerItems.put(plyr, plyrItem);
			switchPlayer.add(plyrItem);
		}
	}
	
	private JMenuItem exitItem()
	{
		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		return exit;
	}
	
	
	
	
	// add a new playerItem
	public void addPlayer()
	{
		switchItem();
	}
	
	public void removePlayer()
	{
		switchItem();
	}
	
	public void switchPlayer() 
	{
		switchItem();
	}
	public void switchHouse()
	{
		
	}
	
	public void placeBet() {}
	public void deal() {}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
