package cs340.server.results;

import java.util.ArrayList;

import model.Game;

public class CreateGameResult {
	private ArrayList<Game> games;

	public CreateGameResult(ArrayList<Game> games) {
		this.games = games;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
}
