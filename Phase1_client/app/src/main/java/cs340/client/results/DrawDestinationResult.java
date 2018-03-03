package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;

public class DrawDestinationResult {
	private ArrayList<DestinationCard> cards;
	private Player player;
	private ArrayList<String> auths;
	
	public DrawDestinationResult(ArrayList<DestinationCard> cards, Player p, ArrayList<String> auths) {
		this.cards = cards;
		this.player = p;
		this.auths = auths;
	}

	public ArrayList<DestinationCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<DestinationCard> cards) {
		this.cards = cards;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<String> getAuths() {
		return auths;
	}

	public void setAuths(ArrayList<String> auths) {
		this.auths = auths;
	}	
}
