package view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;

import model.Player;
import model.interfaces.*;
import view.ViewModel;
import view.interfaces.ViewCallback;



public class GameSummary extends JPanel implements ViewCallback{
	
	private ViewModel model;
	private GameFrame frame;
	
	private final String[] columns = {"ID", "Player", "Bet Placed?"};
	private String[][] rows;
	private JTable table;
	
	//overall status since "selected player" status isn't here
	public GameSummary(ViewModel model) 									
	{
		this.model = model;
		this.frame = model.getFrame();
		setBackground(model.BACKGROUND_COLOR);
		setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
		populate();
		
		model.addViewCallback(this);
	}
	
	private void populate()
	{
		List<Player> players = new ArrayList<>(model.getGameEngine().getAllPlayers());
		rows = new String[players.size()][columns.length];
		for (int i=0; i<rows.length; i++)
		for (int j=0; j<rows[i].length; j++)
		{
			Player plyr = players.get(i);
			switch (columns[j])
			{
				case "ID" : 		rows[i][j] = plyr.getPlayerId();				break;
				case "Player" : 	rows[i][j] = plyr.getName();					break;
				case "Bet Placed?" :rows[i][j] = plyr.getBet() > 0 ? "YES!" : "";	break; 
			}
		}
		
		table = new JTable(rows,columns);
		int tableHeight = frame.getHeight()/4*3;
		table.setPreferredScrollableViewportSize(new Dimension(400,tableHeight));
		//set default height
		if (table.getRowCount() > 0)
			table.setRowHeight(tableHeight/table.getRowCount());
		if (table.getRowCount() > 10)
			table.setRowHeight(tableHeight/10);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setShowGrid(false);
		
		table.setBackground(model.BACKGROUND_COLOR);
		table.setForeground(new Color(0, 100, 150));
		table.setFont(new Font("Impact", Font.PLAIN, 20));
		
		add(new JScrollPane(table));
		
		revalidate();
		repaint();
	}
	
	
	//since JTable works on an "all players" status
	public void placeBet() 
	{
		refresh();
	}
	public void switchPlayer() 
	{
		refresh();
	}
	
	public void switchHouse()
	{
		
	}
	
	public void addPlayer() 		
	{
		refresh();
	}
	
	public void removePlayer() 			
	{
		refresh();
	}
	
	
	public void refresh()
	{
		removeAll();
		populate();
	}

	@Override
	public void deal() {
		// TODO Auto-generated method stub
		
	}

}
