package model.interfaces;

import java.io.File;

/**
 * Assignment interface for SADI representing a PlayingCard
 * 
 * (setting values is handled by the implementing class constructor(s))
 * 
 * @author Caspar Ryan
 * 
 */
public interface PlayingCard
{
   public enum Suit
   {
      HEARTS, SPADES, CLUBS, DIAMONDS;
      
      public String getImageString()
      {
    	  return name().toLowerCase();
      }
      
   }

   public enum Value
   {
      ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
      
      public String getImageString()
      {
    	  switch (this)
    	  {
	    	  	case KING : 	return "king";
				case QUEEN : 	return "queen";
				case JACK : 	return "jack";
				case TEN : 		return "10";
				case NINE :		return "9";
				case EIGHT : 	return "8";
				case SEVEN : 	return "7";
				case SIX : 		return "6";
				case FIVE : 	return "5";
				case FOUR : 	return "4";
				case THREE : 	return "3";
				case TWO : 		return "2";
				case ACE : 		return "ace";
    	  }
    	  return null;
      }
   }

   public static final int DECK_SIZE = 52;

   /**
    * @return the suit of this card based on {@link Suit}
    */
   public Suit getSuit();

   /**
    * @return the face value of this card based on {@link Value}
    */
   public Value getValue();

   /**
    * @return the score value of this card (Ace=1, J, Q, K=10, All others int
    *         of face value)
    */
   public int getScore();

   /**
   * 
   * @return a human readable String that lists the values of this PlayingCard
   *         instance (see OutputTrace.txt)
   *         
   *         e.g. "Suit: Clubs, Value: Five, Score: 5" for Five of Clubs
   */
   @Override
   public abstract String toString();

   /**
    * @param card
    *    another card to compare with
    * @return true if the face value and suit is equal
    */
   public abstract boolean equals(PlayingCard card);
}