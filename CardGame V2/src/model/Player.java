package model;

public class Player extends Holder {

	private String id;
	private int points;
	private int bet;

	public Player(String playerId, String playerName, int initialPoints)
	{
		super(playerName);
		id = playerId;
		points = initialPoints;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPlayerId() {
		return id;
	}

	public boolean placeBet(int bet) {
		if (0 <= bet && bet <= points) {
			this.bet = bet;
			return true;
		} else
			return false;
	}

	public int getBet() {
		return bet;
	}

	public void resetBet() {
		placeBet(0);
	}


	// Player: id=1, name=The Shark, points=900
	public String toString() {
		return String.format("Player: id=%s, name=%s, points=%d", id, super.getName(), points);
	}

}
