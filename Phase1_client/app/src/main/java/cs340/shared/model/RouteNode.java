package model;

public class RouteNode {
	private Route route;
	private boolean visited;
	
	public RouteNode(Route route, boolean v) {
		this.route = route;
		this.visited = v;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
