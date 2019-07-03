package view.GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.*;

import controller.PlayerFunction;
import controller.SelectorListener;
import controller.FunctionListener;
import model.Player;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.ViewModel;
import view.GUI.aesthetics.GameButton;
import view.GUI.aesthetics.PlayerRenderer;
import view.interfaces.GameEngineCallback;
import view.interfaces.ViewCallback;

public class GameTool extends JToolBar implements ViewCallback{
	
	private final Font INPUT_FONT = new Font("Abadi",Font.BOLD, 30);
	private final Font LABEL_FONT = new Font("Dotum", Font.PLAIN, 30);
	
	private JLabel status;
	private JButton betDealButton;
	private JComboBox<Player> selector;
	
	private GameEngine engine;
	private ViewModel model;
	
	// default "no player selected" status
	public GameTool (ViewModel model)										
	{																						
		this.model = model;
		this.engine = model.getGameEngine();
		setFloatable(true);
		
		selector = new JComboBox<>();
		selector.setFont(LABEL_FONT);
		selector.setForeground(Color.LIGHT_GRAY);
		selector.setRenderer(new PlayerRenderer(model));
		setupSelector();
		
		betDealButton = new GameButton("");
		betDealButton.setFont(INPUT_FONT);
		setupBetDealButton(null);
		
		status = new JLabel(String.format("Please %s a player", engine.getAllPlayers().isEmpty() ? "add" : "select"));
		status.setFont(LABEL_FONT);
		status.setForeground(Color.LIGHT_GRAY);
		
		addLayout();
			
		model.addViewCallback(this);
	}
	
	
	
	
	
	
	
	
	
	// since the selector needs to be recreated each time
	private void addLayout()
	{
		setupSelector();
		
		removeAll();
		add(status);
		add(Box.createHorizontalStrut(15));
		add(betDealButton);
		add(Box.createHorizontalGlue());
		add(selector);
	}
	
	private void setupSelector()
	{	
		// so previous listeners don't get called during "setSelectedItem"
		for (ItemListener il : selector.getItemListeners())
			selector.removeItemListener(il);
		
		selector.removeAllItems();
		for (Player plyr : engine.getAllPlayers())
			selector.addItem(plyr);
		
		selector.setSelectedItem((model.getCurrentPlayer() != null) ? model.getCurrentPlayer() : "ok");
		selector.addItemListener(new SelectorListener(model, selector));
	}
	
	private void setupBetDealButton(PlayerFunction function)
	{
		for (ActionListener al : betDealButton.getActionListeners())
			betDealButton.removeActionListener(al);
		if (function != null)
		{
			betDealButton.setText(function.getName());
			betDealButton.addActionListener(new FunctionListener(model, function));
		}
		else betDealButton.setText(null);
	}
	
	
	
	
	
	

	
	
	
	
	
	

	@Override
	// only once the bet is set is the user allowed to deal
	public void placeBet() 
	{
		status.setText("Bet placed!");
		setupBetDealButton(PlayerFunction.DEAL);
		addLayout();
	}

	// serves as a toolbar with a selected "unbetted" player
	@Override
	public void addPlayer() 
	{
		status.setText("");
		setupBetDealButton(PlayerFunction.BET);
		addLayout();
	}
	
	@Override
	public void switchPlayer() 
	{
		Player plyr = model.getCurrentPlayer();
		if (plyr.getBet() > 0)
		{
			if (model.hasDealt(plyr))	deal(); 
			else 						placeBet();
		}
		else addPlayer();
	}
	
	public void switchHouse()
	{
		deal();
	}
	
	@Override
	public void removePlayer() 
	{
		if (model.getCurrentPlayer() == null)
		{
			status.setText("Please add a player");
			setupBetDealButton(null);
		}
		else
			switchPlayer();
	}
	
	@Override
	public void deal() 
	{
		status.setText("Dealt");
		setupBetDealButton(null);
		
		addLayout();
	}
	
	

}
