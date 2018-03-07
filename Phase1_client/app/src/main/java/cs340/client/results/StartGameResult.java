package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;

public class StartGameResult {
	private ArrayList<Player> players;
	private ArrayList<Game> games;
	private Game startedGame;

	public StartGameResult(ArrayList<Player> players, Game game) {
		this.players = players;
		this.startedGame = game;
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

	public Game getStartedGame() {
		return startedGame;
	}

	public void setStartedGame(Game startedGame) {
		this.startedGame = startedGame;
	}
}