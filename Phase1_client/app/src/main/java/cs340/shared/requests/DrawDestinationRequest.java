package cs340.shared.requests;

import cs340.shared.model.Player;

public class DrawDestinationRequest {
	private int gameID;
	private Player player;

	public DrawDestinationRequest(int gameID, Player player) {
		this.gameID = gameID;
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public int getGameID() {
		return gameID;
	}
}
