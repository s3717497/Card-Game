package client;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.Player;
import model.PlayingCardImpl;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.GameEngineCallbackGUI;
//import validate.Validator;
import view.GameEngineCallbackImpl;


public class SimpleTestClient
{
   private static Logger logger = Logger.getLogger("assignment1");

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();

      //Validator.validate(true);

      Player[] players = new Player[] 
      { 
          new Player("1", "The Shark", 1000), 
          new Player("2", "The Loser", 500),
          new Player("3", "Dateum", 2000),
          new Player("4", "Plinga", 2000),
          new Player("5", "Rupesh", 2000),
          new Player("6", "Nihao", 2000),
          new Player("7", "Destroyer", 2000),
          new Player("8", "Pandemic", 2000),
          new Player("9", "Stickyum", 2000),
          new Player("10", "Tellooran", 2000),
          new Player("11", "Spacecraft", 2000),
          new Player("12", "Analogous", 2000),
          new Player("13", "Stankpop", 2000),
          new Player("14", "Soap Dragon", 2000)
          
      };

      for (Player player : players)
         gameEngine.addPlayer(player);
      
      //create a house object
      // and set it's name
      
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
      gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngine));
      
//      for (Player player : players)
//      {
//         gameEngine.addPlayer(player);
//         gameEngine.placeBet(player, 100);
//         gameEngine.dealPlayer(player, 1);
//      }
//      gameEngine.dealHouse(10);
   }

   private static void printCards(Deque<PlayingCard> deck)
   {
      for (PlayingCard card : deck)
         logger.log(Level.INFO, card.toString());
   }
}
