package cs340.server.requests;

import model.Player;

public class CreateGameRequest {
	private String name;
	private Player player;
	private int capacity;
	private String color;
	
	public CreateGameRequest(String name, Player player, int capacity, String color) {
		this.name = name;
		this.player = player;
		this.capacity = capacity;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
