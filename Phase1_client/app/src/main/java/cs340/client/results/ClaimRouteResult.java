package cs340.client.results;

import java.util.ArrayList;

import cs340.shared.model.Route;

public class ClaimRouteResult {
	private String username;
	private Route route;
	ArrayList<String> auths;
	
	public ClaimRouteResult(String username, Route route, ArrayList<String> auths) {
		this.username = username;
		this.route = route;
		this.auths = auths;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public ArrayList<String> getAuths() {
		return auths;
	}

	public void setAuths(ArrayList<String> auths) {
		this.auths = auths;
	}
}
