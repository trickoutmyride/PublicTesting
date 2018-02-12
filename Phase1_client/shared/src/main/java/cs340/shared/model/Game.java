package cs340.shared.model;

import java.util.ArrayList;

public class Game {
	/* Fields */
	private String gameName;
	private int gameID;
	private ArrayList<Player> players;
	private Player owner;
	private int capacity;
	
	/* "Maybe Later" Fields */
	//private Map gameMap;
	//private TrainCard[5] shownCards;
	//private Stack<TrainCard> deck;
	
	/* Methods */
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public int getCapacity(){ return capacity; };
	public void setCapacity(int capacity){ this.capacity = capacity; }
	public Player getOwner(){ return owner;}
	public void setOwner(Player owner) {this.owner = owner;}
}
