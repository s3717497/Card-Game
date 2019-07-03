package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private Map<String, Player> players;
	private Holder house;
	private List<GameEngineCallback> callbacks;
	private Deque<PlayingCard> deck;
	
	public GameEngineImpl()
	{
		players = new HashMap<>();
		house = new House("Blackjack House");
		callbacks = new ArrayList<>();
		deck = getShuffledDeck();
	}
		
	
	
	public void deal(Holder holder, int delay)
	{
		int result = dealTillBust(holder, delay, 0);
		holder.setResult(result);
		
		if (holder instanceof House)
			betReturns(holder.getResult());
		
		for (GameEngineCallback callback : callbacks)	callback.bustCard(holder, deck.getFirst(), this);
		for (GameEngineCallback callback : callbacks)	callback.result(holder, result, this);
		removeAndRefill();
	}
	
//	deals cards to player/house
//	returns result of house/player
//	score default to 0 unless the deal is stopped
	private int dealTillBust(Holder holder, int delay, int score)
	{
		PlayingCard card = deck.getFirst();					/*deck.getFirst is BEFORE refill*/
		delay(delay);
		
		//	nextCardScore 	+ score > 21, busted!
		if (card.getScore() + score > GameEngine.BUST_LEVEL) 
			return score;
		
		for (GameEngineCallback callback : callbacks)	callback.nextCard(holder, card, this);
		removeAndRefill();
		// update score and keep dealing
		return dealTillBust(holder, delay, score + card.getScore());
	}
	
	private void removeAndRefill()
	{
		deck.removeFirst();
		if (deck.isEmpty())	
			deck = getShuffledDeck();
	}
	
	
	
	
// 	compare house results with players [win/loss values]
// 	update their points based on their bet
	private void betReturns(int houseResult) {
		for (Player plyr : players.values()) 
		{
			int playerResult= plyr.getResult();
			int points 		= plyr.getPoints();
			int bet			= plyr.getBet();
			
			if 		(playerResult > houseResult)	plyr.setPoints(points + bet);
			else if (playerResult < houseResult)	plyr.setPoints(points - bet);
			plyr.resetBet();
		}
	}
	
// 	code reuse as this serves as a searchPlayer method
// 	with same id
	public Player getPlayer(String id) {
		return players.get(id);
	}
	
	public void addPlayer(Player player) {
		//if there is a player with this id
		//that player is returned
		String id = player.getPlayerId();
		players.put(id, player);
	}

	public boolean removePlayer(Player player) 
	{
		return players.remove(player.getPlayerId()) != null;
	}
	
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(players.values());
	}
	
	public Holder getHouse() {
		return house;
	}

	
	
	
	
	
	
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}
	
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return callbacks.remove(gameEngineCallback);
	}

	
	
	

	/*where to place bet?*/
	public boolean placeBet(Player player, int bet) {
		// if player already betted
		if (player.getBet() > 0)
			return false;
		return player.placeBet(bet);
	}

	public Deque<PlayingCard> getShuffledDeck() 
	{
		List<PlayingCard> deck = new ArrayList<PlayingCard>();
		for (Suit suit : Suit.values())
		for (Value val: Value.values())
			deck.add(new PlayingCardImpl(suit, val));
		
		Collections.shuffle(deck);
		return new ArrayDeque<PlayingCard>(deck);
	}
	
	private void delay(int delay) {
		try 
		{
			TimeUnit.MILLISECONDS.sleep(delay);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
	}

}
