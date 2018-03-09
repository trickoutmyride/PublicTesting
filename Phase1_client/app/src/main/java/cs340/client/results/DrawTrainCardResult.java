package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;

public class DrawTrainCardResult {
	private TrainCard card;
	private int index;
	private Player player;
	private ArrayList<String> auths;

	public DrawTrainCardResult(TrainCard card, Player player, ArrayList<String> auths) {
		this.card = card;
		this.index = index;
		this.player = player;
		this.auths = auths;
	}

	public TrainCard getCard() {
		return card;
	}

	public void setCard(TrainCard card) {
		this.card = card;
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
