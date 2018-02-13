package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;

public class StartGameResult {
	private ArrayList<Player> players;
	private ArrayList<Game> games;

	public StartGameResult(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
}