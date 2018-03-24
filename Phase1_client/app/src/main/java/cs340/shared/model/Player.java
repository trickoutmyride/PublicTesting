package cs340.shared.model;

import java.util.ArrayList;

public class Player {
	/* Fields */
	private String username;
	private String password;
	private String authToken;
	private ArrayList<TrainCard> cards;
	private ArrayList<DestinationCard> destinations;
	private ArrayList<Route> claimedRoutes;
	private int points;
	private int claimedRoutePoints;
	private int longestRoutePoints;
	private int reachedDestinationPoints;
	private int unreachedDestinationPoints;
	private int trainCars;


	public Player(String u, String p, String a){
		this.username = u;
		this.password = p;
		this.authToken = a;
		this.cards = new ArrayList<TrainCard>();
		this.destinations = new ArrayList<DestinationCard>();
		this.claimedRoutes = new ArrayList<Route>();
	}
	
	/* Methods */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getAuthToken() {
		return authToken;
	}
	public ArrayList<TrainCard> getCards() {
		return cards;
	}
	public ArrayList<DestinationCard> getDestinations() {
		return destinations;
	}
	public ArrayList<Route> getClaimedRoutes() {
		return claimedRoutes;
	}
	public int getPoints() {
		return points;
	}
	public int getTrainCars() {
		return trainCars;
	}
	public int getClaimedRoutePoints() { return claimedRoutePoints; }
	public int getLongestRoutePoints() { return longestRoutePoints;	}
	public int getReachedDestinationPoints() { return reachedDestinationPoints;	}
	public int getUnreachedDestinationPoints() { return unreachedDestinationPoints;	}

}
