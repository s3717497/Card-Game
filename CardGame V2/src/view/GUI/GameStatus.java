package view.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.*;

import model.Holder;
import model.House;
import model.Player;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.ViewModel;
import view.GUI.aesthetics.GameLabel;
import view.interfaces.GameEngineCallback;
import view.interfaces.ViewCallback;

public class GameStatus extends JPanel implements ViewCallback{

	private GameLabel playerStatus;
	private GameLabel pointsStatus;
	private GameLabel betStatus;
	
	private ViewModel model;
	private GameFrame frame;
	
	// in "no player" state
	public GameStatus(ViewModel model)
	{
		this.model = model;
		this.frame = model.getFrame();
		setLayout(new GridLayout(1, 3));
		setBackground(new Color(50,50,50));
		
		playerStatus = new GameLabel(model, "No player selected", SwingConstants.LEFT);
		pointsStatus = new GameLabel(model, "No points", SwingConstants.CENTER);
		betStatus = new GameLabel(model, "Not betted", SwingConstants.RIGHT);
		
		GameLabel[] labels = {playerStatus, pointsStatus, betStatus};
		for (GameLabel label : labels)
		{
			label.setForeground(Color.GRAY);
			label.setFont(label.getFont().getFamily(),label.getFont().getStyle(), 10);
		}
		playerStatus.setForeground(Color.GRAY);
		pointsStatus.setForeground(Color.GRAY);
		betStatus.setForeground(Color.GRAY);

		// add three labels
		add(playerStatus);
		add(pointsStatus);
		add(betStatus);
		

		model.addViewCallback(this);
	}
	
	// small class, doesn't need to reload entire panel
	@Override
	public void placeBet() 
	{
		int bet = model.getCurrentPlayer().getBet();
		betStatus.setText((bet > 0) ? "Bet amount: " + bet : "Bet not placed");
	}

	@Override
	public void addPlayer() 
	{
		switchPlayer();
	}

	@Override
	public void switchPlayer() 
	{
		switchHolder();
		pointsStatus.setText("Playing with " + model.getCurrentPlayer().getPoints() + " points");
		placeBet();
	}
	
	public void switchHouse()
	{
		switchHolder();
		pointsStatus.setText("");
		betStatus.setText("");
	}
	
	public void switchHolder()
	{
		Holder holder = model.getCurrentHolder();
		playerStatus.setText("Holder: " + holder.getName());
	}
	@Override
	public void removePlayer() 
	{
		if (model.getCurrentPlayer() == null)
		{
			frame.remove(model.getStatus());
			model.setGameStatus(new GameStatus(model));
			frame.add(model.getStatus());
			revalidate();
			repaint();
		}
		else
			switchPlayer();
	}
	
	

	@Override
	public void deal() {}
	

}
