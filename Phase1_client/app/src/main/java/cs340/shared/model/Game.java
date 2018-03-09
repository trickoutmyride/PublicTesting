package cs340.shared.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	/* Fields */
	private String gameName;
	private int gameID;
	private boolean _hasStarted = false;
	private ArrayList<Player> players;
	private int capacity;
	private ArrayList<ErrorObserver> errorObservers = new ArrayList<>();
	private ArrayList<LobbyObserver> lobbyObservers = new ArrayList<>();
	private HashMap<String,String> colors;
	private int playerCount;
	private boolean started;
	private int turn; //Index of player who is currently taking a turn
	private ArrayList<Route> unclaimedRoutes;
	private ArrayList<TrainCard> trainDeck;
	private ArrayList<TrainCard> trainFaceup;
	private GameMap gameMap;

	/* "Maybe Later" Fields */
	//private Map gameMap;
	//private TrainCard[5] shownCards;
	//private Stack<TrainCard> deck;

	public Game(String gameName, ArrayList<Player> players, int capacity) {
		this.gameName = gameName;
		this.players = players;
		this.capacity = capacity;
		this.playerCount = 1;
		//this.gameID = ServerModel.getNextGameID();
		this.colors = new HashMap<String,String>();
		this._hasStarted = false;
		this.gameMap = new GameMap(this);
	}

	/* Methods */
	public void addErrorObserver(ErrorObserver observer) {
		errorObservers.add(observer);
	}
	public void addLobbyObserver(LobbyObserver observer) {
		lobbyObservers.add(observer);
		addErrorObserver(observer);
	}

	public GameMap getGameMap() {
		if (gameMap == null) gameMap = new GameMap(this);
		return gameMap;
	}

	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public boolean hasStarted() {
		return _hasStarted;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		for (LobbyObserver observer : lobbyObservers)
			observer.onRosterUpdated(players);
	}
	public int getCapacity(){ return capacity; };
	public void setCapacity(int capacity){ this.capacity = capacity; }
	public void removeErrorObserver(ErrorObserver observer) {
		errorObservers.remove(observer);
	}
	public void removeLobbyObserver(LobbyObserver observer) {
		lobbyObservers.remove(observer);
		removeErrorObserver(observer);
	}
	public void sendError(String message) {
		for (ErrorObserver observer : errorObservers) observer.onError(message);
	}
	public void start() {
		_hasStarted = true;
		for (LobbyObserver observer : lobbyObservers) observer.onGameStarted();
	}

	interface ErrorObserver {
		void onError(String message);
	}

	public interface LobbyObserver extends ErrorObserver {
		void onGameStarted();
		void onRosterUpdated(ArrayList<Player> players);
	}

	public HashMap<String,String> getColors(){
		return this.colors;
	}

	public void setColor(String username, String color){
		this.colors.put(username, color);
	}

	public ArrayList<String> getRemainingColors(){
		ArrayList<String> remainingColors = new ArrayList<String>();
		boolean blue = false;
		boolean red = false;
		boolean green = false;
		boolean yellow = false;
		boolean black = false;

		//Iterates through all players
		for(Player p : this.players){
			String color = this.colors.get(p.getUsername()); //Gets the color and sets the flags for which ones have been taken
			if(color.equals("blue")){
				blue = true;
			}else if(color.equals("red")){
				red = true;
			}else if(color.equals("green")){
				green = true;
			}else if(color.equals("yellow")){
				yellow = true;
			}else if(color.equals("black")){
				black = true;
			}
		}

		//Adds colors to the remaining list based on which ones are available
		if(!blue) remainingColors.add("blue");
		if(!red) remainingColors.add("red");
		if(!green) remainingColors.add("green");
		if(!yellow) remainingColors.add("yellow");
		if(!black) remainingColors.add("black");

		return remainingColors;
	}

	public ArrayList<TrainCard> getTrainFaceup() {
		return trainFaceup;
	}
}
