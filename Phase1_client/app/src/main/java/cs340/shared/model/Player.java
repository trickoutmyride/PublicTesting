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
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public ArrayList<TrainCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<TrainCard> cards) {
		this.cards = cards;
	}

	public ArrayList<DestinationCard> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<DestinationCard> destinations) {
		this.destinations = destinations;
	}

	public ArrayList<Route> getClaimedRoutes() {
		return claimedRoutes;
	}

	public void setClaimedRoutes(ArrayList<Route> claimedRoutes) {
		this.claimedRoutes = claimedRoutes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTrainCars() {
		return trainCars;
	}

	public void setTrainCars(int trainCars) {
		this.trainCars = trainCars;
	}
}
