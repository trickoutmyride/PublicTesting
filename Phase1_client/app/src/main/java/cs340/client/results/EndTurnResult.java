package cs340.client.results;

import cs340.shared.model.Game;

public class EndTurnResult {
	private Game game;

	public EndTurnResult(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
