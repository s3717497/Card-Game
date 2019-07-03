package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Holder;
import model.House;
import model.Player;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */


public class GameEngineCallbackImpl implements GameEngineCallback
{
   private final Logger logger = Logger.getLogger(this.getClass().getName());
   
   public GameEngineCallbackImpl()
	{
		logger.setLevel(Level.FINE);
	}
   
   
   
   
   
   
//   public void nextCard(Player player, PlayingCard card, GameEngine engine) 
//   {
//	   	String playerName = player.getPlayerName();
//	   	logger.log(Level.FINE, nextCall(playerName,card));
//   }
//
//	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
//	{
//		String playerName = player.getPlayerName();
//		logger.log(Level.FINE, bustCall(playerName,card));
//	}
//	
// 	public void nextHouseCard(PlayingCard card, GameEngine engine) 
// 	{
// 		logger.log(Level.FINE, nextCall("House",card));
// 	}
//
// 	public void houseBustCard(PlayingCard card, GameEngine engine) 
// 	{
// 		logger.log(Level.FINE, bustCall("House",card));
// 	}
   
   public void nextCard(Holder holder, PlayingCard card, GameEngine engine) 
   {
	   	logger.log(Level.FINE, nextCall(holder.getName(),card));
   }

   public void bustCard(Holder holder, PlayingCard card, GameEngine engine) 
   {
	   	logger.log(Level.FINE, bustCall(holder.getName(),card));
   }

 	
 	
 	
	// logs both house result and final player results
	// AFTER previous results calculated
	public void result(Holder holder, int result, GameEngine engine) 
	{
		String results = resultCall(holder.getName(), result);
		
		if (holder instanceof House)
		{
			results += "\nFinal Player Results";
			for (Player plyr : engine.getAllPlayers()) 
				results += "\n" + plyr.toString();
		}
		
		logger.log(Level.INFO, results);
		
	}
	
	
	
	
	
	
	
	   
	// for either nextCard/nextHouseCard
	// name : either player/house name
	private String nextCall(String name, PlayingCard card) 
	{
		return "Card Dealt to " + name + "\t" + card.toString();
	}
   
	private String bustCall(String name, PlayingCard card) 
	{
		return nextCall(name,card) + "\tYOU BUSTED";
	}
   
	private String resultCall(String name, int result) 
	{
		return name + ", final result=" + result;
	}

}
