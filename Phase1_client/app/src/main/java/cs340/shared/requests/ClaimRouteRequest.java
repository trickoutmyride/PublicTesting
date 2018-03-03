package cs340.shared.requests;

import cs340.shared.model.Player;

public class ClaimRouteRequest {
	private Player player;
	private String start;
	private String end;

	public ClaimRouteRequest(Player player, String start, String end) {
		this.player = player;
		this.start = start;
		this.end = end;
	}

	public Player getPlayer() {
		return player;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}