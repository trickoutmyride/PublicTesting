package cs340.shared.requests;

import cs340.shared.model.Player;

public class DrawDestinationRequest {
	private Player p;

	public DrawDestinationRequest(Player p) {
		this.p = p;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
}

