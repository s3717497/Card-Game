package view;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Holder;
import model.House;
import model.Player;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

// Treated like an updatee, whose functions are called from game engine rather than controller
public class GameEngineCallbackGUI implements GameEngineCallback {

	private ViewModel model;											
	public GameEngineCallbackGUI(GameEngine engine)
	{
		model = new ViewModel(engine);
	}
	
	// besides CardPanel, all other panels only have single methods based on GUICallback
	@Override
	public void nextCard(Holder holder, PlayingCard card, GameEngine engine) 
	{
		model.setLastDealtCard(holder, card);
		model.getCardPanel().nextCard(holder, card, engine);
		
	}

	@Override
	public void bustCard(Holder holder, PlayingCard card, GameEngine engine) 
	{
		model.setLastDealtCard(holder, card);
		model.getCardPanel().bustCard(holder, card, engine);
	}

	@Override
	public void result(Holder holder, int result, GameEngine engine) 
	{
		model.getCardPanel().result(holder, result, engine);
		
		if (holder instanceof Player)
		{
			if (model.readyToDealHouse())
			{
				model.switchHouse(engine.getHouse());
				model.deal();
			}
				
		}
		else
		{
			model.getGameSummary().refresh();
		}
	}


}
	
//	// besides CardPanel, all other panels only have single methods based on GUICallback
//	@Override
//	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().nextCard(player, card, engine);
//			}
//		});
//	}
//
//	@Override
//	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().bustCard(player, card, engine);
//			}
//		});
//	}
//
//	@Override
//	public void result(Player player, int result, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().result(player, result, engine);
//				model.notifyResult();
//			}
//		});
//	}
//
//	@Override
//	public void nextHouseCard(PlayingCard card, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().nextHouseCard(card, engine);
//				model.getStatus().houseState();
//				model.dealPlayer();
//			}
//		});
//		
//	}
//
//	@Override
//	public void houseBustCard(PlayingCard card, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().houseBustCard(card, engine);
//			}
//		});
//	}
//
//	@Override
//	public void houseResult(int result, GameEngine engine) 
//	{
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				model.getCardPanel().houseResult(result, engine);
//				model.getGameSummary().refresh();
//				
//			}
//		});
//		
//	}


