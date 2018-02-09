package cs340.shared.model;

import java.util.ArrayList;

public class ServerModel {
	/* Singleton */
	private static ServerModel instance;
	/* Fields */
	private GameList games;
	private ArrayList<Player> players;
	
	//Constructor
	private ServerModel() {}
	
	public static ServerModel getInstance(){
		if(instance == null){
			instance = new ServerModel();
		}
		return instance;
	}
	

	/* Methods */
	//Games getters
	public static GameList getGames() {
		return getInstance().getGamesInner();
	}
	
	private GameList getGamesInner(){
		return games;
	}
	
	//Games setters
	public static void setGames(GameList games){
		getInstance().setGamesInner(games);
	}
	
	private void setGamesInner(GameList games) {
		this.games = games;
	}
	
	//Player list getters
	public static ArrayList<Player> getPlayers(){
		return getInstance().getPlayersInner();
	}
	
	private ArrayList<Player> getPlayersInner() {
		return players;
	}
	
	//Player list setters
	public static void setPlayers(ArrayList<Player> players){
		getInstance().setPlayersInner(players);
	}
	
	private void setPlayersInner(ArrayList<Player> players) {
		this.players = players;
	}
	
}
