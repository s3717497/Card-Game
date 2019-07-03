package view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Holder;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.ViewModel;
import view.GUI.aesthetics.GameLabel;
import view.interfaces.GameEngineCallback;
import view.interfaces.ViewCallback;


public class CardPanel extends JPanel implements ViewCallback, GameEngineCallback{

	
	private GameLabel cardLabel;
	private GameLabel cardIcon;
	private GameLabel holderLabel;
	private GameLabel resultLabel;
	
	private JPanel disCard;
	private GameLabel disCardLabel;
	
	
	private ViewModel model;
	private GameFrame frame;
	
	// created with "no player dealt" status initially
	public CardPanel (ViewModel model)
	{
		
		this.model = model;
		this.frame = model.getFrame();
		setLayout(new BorderLayout());
		setBackground(model.BACKGROUND_COLOR);
		
		disCard = new JPanel();
		disCard.setBackground(new Color(50,50,50));
		disCardLabel =  new GameLabel(model, "Discard");
		disCardLabel.setForeground(model.BACKGROUND_COLOR);
		disCard.add(disCardLabel);
		
		holderLabel = new GameLabel(model, "Player not dealt");
		holderLabel.setForeground(new Color(63,63,63));
		
		cardIcon = new GameLabel(model);
		cardLabel = new GameLabel(model);
		
		
		resultLabel = new GameLabel(model,  " ");
		resultLabel.setForeground(Color.WHITE);
		
		// custom methods via GameLabel
		cardLabel.setFont("Abadi", Font.BOLD,  30);
		holderLabel.setFont("Abadi", Font.PLAIN,  20);
		resultLabel.setFont("Abadi", Font.PLAIN,  12);
		disCardLabel.setFont("Gulim", Font.BOLD, 16);

		
		
		add(center(holderLabel), BorderLayout.NORTH);
		add(mainCardBox(),BorderLayout.CENTER);
		add(disCard, BorderLayout.WEST);
		add(center(resultLabel), BorderLayout.SOUTH);
		
		model.addViewCallback(this);
	}
	

	
	private Box center(Component comp)
	{
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(Box.createHorizontalStrut(7));
		box.add(comp);
		box.add(Box.createHorizontalStrut(7));
		box.add(Box.createHorizontalGlue());
		return box;
	}
	
	private Box mainCardBox()
	{
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalGlue());
		box.add(center(cardIcon));
		box.add(center(cardLabel));
		box.add(Box.createVerticalGlue());
		return box;
	}
	
	
	private void refreshDisCard()
	{
		//clone
		Holder currentHolder = model.getCurrentHolder();
		LinkedList<PlayingCard> cards = new LinkedList<PlayingCard>(currentHolder != null ? model.getAllDealtCards(currentHolder) : null);
		// since discard excludes the last (main) card
		if (cards.size() > 0)
			cards.removeLast();
		
		disCard.removeAll();
		if (cards.isEmpty())
		{
			disCard.add(disCardLabel);
			return;
		}
		
		
		disCard.setLayout(new GridLayout(4,1));
		for (PlayingCard card : cards)
			disCard.add(center(new GameLabel(model, card, 45)));
		revalidate();
	}
	


	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void placeBet() 
	{
		holderLabel.setText("Ready to deal");
		
	}

	@Override
	public void addPlayer() 
	{
		switchPlayer();
	}
	
	@Override
	public void removePlayer() 
	{
		switchPlayer();
	}
	

	// either display no card, or the last card in hashmap
	@Override
	public void switchPlayer() 
	{
		switchHolder();
	}
	
	public void switchHouse() 
	{
		switchHolder();
	}
	
	public void switchHolder()
	{
		Holder currentHolder 	= model.getCurrentHolder();
		PlayingCard currentCard = model.getLastDealtCard(currentHolder);
		
		if (currentCard == null)
		{
			frame.remove(model.getCardPanel());
			model.setCardPanel(new CardPanel(model));
			frame.add(model.getCardPanel());
			revalidate();
			repaint();
		}
		else 
			cardCall(currentHolder, currentCard);
	}
	
	@Override
	public void deal() 
	{
		holderLabel.setText("Dealing");
	}
	
	
	
	
	
	
	
	
	@Override
	public void nextCard(Holder holder, PlayingCard card, GameEngine engine) 
	{
		cardCall(holder, card);
	}

	@Override
	public void bustCard(Holder holder, PlayingCard card, GameEngine engine) 
	{
		cardCall(holder, card);
	}

	@Override
	public void result(Holder holder, int result, GameEngine engine) 
	{
		// to prevent a card call overriding a player switch
		cardCall(holder,model.getLastDealtCard(holder));
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	private void cardCall(Holder holder, PlayingCard card)
	{
		// to prevent a card call overriding a holder switch
		if (holder.equals(model.getCurrentHolder()))
			labelUpdate(card, holder.getResult(), holder.getName().toUpperCase());
	}
	
	private void labelUpdate(PlayingCard card, int result, String message)
	{
		
		resultLabel.setText(" ");
		holderLabel.setText("Dealing");
		cardLabel.setText(card.getValue() + " of " + card.getSuit());
		cardIcon.setIcon(card, 200);
		
		refreshDisCard();
		// if finished dealing
		if (result > 0)
		{
			resultLabel.setText("Final result: " + result);
			holderLabel.setForeground(new Color(140,0,0));
			holderLabel.setFont("Impact", Font.PLAIN);
			holderLabel.setText(message + " BUSTED!");
		}
	}
	
	
}
