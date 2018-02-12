package cs340.shared.model;

import java.util.ArrayList;

public class Game {
	/* Fields */
	private String gameName;
	private int gameID;
	private boolean _hasStarted = false;
	private ArrayList<Player> players;
	private int capacity;
	private ArrayList<ErrorObserver> errorObservers = new ArrayList<>();
	private ArrayList<LobbyObserver> lobbyObservers = new ArrayList<>();
	
	/* "Maybe Later" Fields */
	//private Map gameMap;
	//private TrainCard[5] shownCards;
	//private Stack<TrainCard> deck;
	
	/* Methods */
	public void addErrorObserver(ErrorObserver observer) {
		errorObservers.add(observer);
	}
	public void addLobbyObserver(LobbyObserver observer) {
		lobbyObservers.add(observer);
		addErrorObserver(observer);
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
}
