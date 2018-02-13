package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Game;

public class JoinGameResult {
	private ArrayList<Game> games;
	private Game game;
	private String error;
	
	public JoinGameResult(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
}
