package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;

public class SignInResult {
	private Player player;
	private ArrayList<Game> gamelist;

	public SignInResult(Player p, ArrayList<Game> games){
		player = p;
		gamelist = games;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Game> getGamelist() {
		return gamelist;
	}

	public void setGamelist(ArrayList<Game> gamelist) {
		this.gamelist = gamelist;
	}
}
