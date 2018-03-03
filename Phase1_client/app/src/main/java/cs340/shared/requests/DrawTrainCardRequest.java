package cs340.shared.requests;

import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;

public class DrawTrainCardRequest {
	private int gameID;
	private TrainCard card;
	private Player player;

	public DrawTrainCardRequest (int gameID, TrainCard card, Player player) {
		this.gameID = gameID;
		this.card = card;
		this.player = player;
	}

	public int getGameID() {
		return gameID;
	}

	public Player getPlayer() {
		return player;
	}

	public TrainCard getCard() {
		return card;
	}
}
