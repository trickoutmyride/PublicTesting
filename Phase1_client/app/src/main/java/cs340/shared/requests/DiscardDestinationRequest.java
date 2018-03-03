package cs340.shared.requests;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;

public class DiscardDestinationRequest {
	private int gameID;
	private ArrayList<DestinationCard> cards;
	private Player p;
	
	public DiscardDestinationRequest(int gameID, ArrayList<DestinationCard> cards, Player p) {
		this.gameID = gameID;
		this.cards = cards;
		this.p = p;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public ArrayList<DestinationCard> getCards() {
		return cards;
	}
	public void setCards(ArrayList<DestinationCard> cards) {
		this.cards = cards;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
}
