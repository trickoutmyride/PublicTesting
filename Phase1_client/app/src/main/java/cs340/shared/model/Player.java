package cs340.shared.model;

public class Player {
	/* Fields */
	private String username;
	private String password;
	private String authToken;

	public Player(String u, String p, String a) {
		this.username = u;
		this.password = p;
		this.authToken = a;
	}

	/* Everything below here are "maybe" fields; we might use these as is for later phases, or we might not  */
	//private int points;
	//private int trainCars;
	//private ArrayList<TrainCard> cards;
	//private ArrayList<DestinationTicket> destinations;
	//private ArrayList<City> routes;

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

}
