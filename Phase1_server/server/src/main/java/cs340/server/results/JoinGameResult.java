package cs340.server.requests;

import model.Game;

public class JoinGameResult {
	private Game game;

	public JoinGameResult(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
