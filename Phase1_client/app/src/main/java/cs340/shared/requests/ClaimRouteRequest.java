package cs340.shared.requests;

import cs340.shared.model.Player;
import cs340.shared.model.Route;

public class ClaimRouteRequest {
	private Player player;
	private Route route;

	public ClaimRouteRequest(Player player, Route route) {
		this.player = player;
		this.route = route;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
