package controller;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import model.Player;
import view.ViewModel;

public class SelectorListener implements ItemListener{
	
	private ViewModel model;
	private JComboBox<Player> playerList;
	public SelectorListener(ViewModel model, JComboBox<Player> playerList)
	{
		this.model = model;
		this.playerList = playerList;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if (e.getStateChange() == ItemEvent.SELECTED)
			model.switchPlayer((Player) playerList.getSelectedItem());
	}
	

}
