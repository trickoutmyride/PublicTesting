package cs340.shared.requests;

import cs340.shared.model.Player;

public class ChatRequest {
	private Player player;
	private String message;
	
	public ChatRequest(Player player, String message) {
		this.player = player;
		this.message = message;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
