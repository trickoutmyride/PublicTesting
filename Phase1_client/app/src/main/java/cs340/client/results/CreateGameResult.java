package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Game;

public class CreateGameResult {
	private ArrayList<Game> games;
	private Game game;
	private String error;
	
	public CreateGameResult(ArrayList<Game> games) {
		this.games = games;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}