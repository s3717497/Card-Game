package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard{

	private PlayingCard.Suit suit;
	private PlayingCard.Value value;
	
	public PlayingCardImpl(PlayingCard.Suit suit, PlayingCard.Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	
	
	
	
	public Suit getSuit() {
		return suit;
	}

	public Value getValue() {
		return value;
	}

	public int getScore() {
		switch (value)
		{
			case KING : 
			case QUEEN : 
			case JACK : 
			case TEN : 		return 10;
			case NINE :		return 9;
			case EIGHT : 	return 8;
			case SEVEN : 	return 7;
			case SIX : 		return 6;
			case FIVE : 	return 5;
			case FOUR : 	return 4;
			case THREE : 	return 3;
			case TWO : 		return 2;
			case ACE : 		return 1;
			default : 		return -1;
		}
	}
	
	public boolean equals(PlayingCard card) {
		if (this.getValue() == card.getValue() &&
			this.getSuit() 	== card.getSuit())
			 return true; 
		else return false;
	}
	
	// hashCode that is unique to each card
	// since this satisfies the conditions
	public int hashCode() {
		int hash = 0;
		switch (suit)
		{
			case CLUBS : 	hash = 100;
			case DIAMONDS :	hash = 200;
			case HEARTS :	hash = 300;
			case SPADES :	hash = 400;
		}
		
		switch (value)
		{
			case KING : 	return hash+13;
			case QUEEN : 	return hash+12;
			case JACK : 	return hash+11;
			case TEN : 		return hash+10;
			case NINE :		return hash+9;
			case EIGHT : 	return hash+8;
			case SEVEN : 	return hash+7;
			case SIX : 		return hash+6;
			case FIVE : 	return hash+5;
			case FOUR : 	return hash+4;
			case THREE : 	return hash+3;
			case TWO : 		return hash+2;
			case ACE : 		return hash+1;
			default : 		return 	   -1;
		}
	}
	
//	Suit: CLUBS, Value: TWO, Score: 2
	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d", getSuit(),getValue(),getScore());
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;		/*WHY if there's a more detailed check after*/
		if (obj == null)	return false;
		if (!(obj instanceof PlayingCardImpl))
			return false;
		
		PlayingCardImpl object = (PlayingCardImpl) obj;
		return (suit == object.suit && value == object.value);
	}
	
	
	

}
