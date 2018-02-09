package cs340.server.results;

import java.util.ArrayList;

import model.Game;
import model.Player;

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
