package requests;

import model.Player;

public class JoinGameRequest {
	private int gameID;
	private Player player;
	private String color;
	
	public JoinGameRequest(int gameID, Player player, String color) {
		this.gameID = gameID;
		this.player = player;
		this.color = color;
	}
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
