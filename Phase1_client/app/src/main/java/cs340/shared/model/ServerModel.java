package cs340.shared.model;

import java.util.ArrayList;

public class ServerModel {
	/* Singleton */
	private static ServerModel instance;
	/* Fields */
	private static GameList games;
	private static ArrayList<Player> players;
	
	//Constructor
	private ServerModel() {
		this.players = new ArrayList<Player>();
		this.games = new GameList();
	}
	
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
	
	//Adds a player to the player list
	public static void addPlayerList(Player p){
		getInstance().addPlayerListInner(p);
	}
	
	private void addPlayerListInner(Player p){
		this.players.add(p);
	}
	
	//Adds a game to the game list
	public static String addGame(Game g){
		return getInstance().addGameInner(g);
	}
	
	private String addGameInner(Game g){
		return this.games.addGame(g);
	}
	
	//Gets the next game ID
	public static int getNextGameID(){
		return getInstance().games.getGames().size();
	}
	
	//Starts the game
	public static void startGame(int id){
		getInstance().startGameInner(id);
	}
	
	private void startGameInner(int id){
		this.games.getGames().get(id).startGame();
	}
	
	//Draws destination cards for a player
	public static ArrayList<DestinationCard> drawDestinations(Player p){
		return getInstance().drawDestinationsInner(p);
	}
	
	private ArrayList<DestinationCard> drawDestinationsInner(Player p){
		Game g = this.getGameByPlayerInner(p); //Gets the game
		ArrayList<DestinationCard> newDestinations = new ArrayList<DestinationCard>();
		
		for(int i = 0; i < 3; i++){ //Attempts to draw 3 cards
			if(g.getDestinationDeck().size() == 0){ //If there aren't any cards
				break; //Break the loop
			}else{
				newDestinations.add(g.drawDestination()); //Add a destination card to the "drawn" array
			}
		}
		
		g.getPlayer(p.getAuthToken()).getDestinations().addAll(newDestinations); //Adds them to the player
		g.getHistory().add(p.getUsername()+" drew "+newDestinations.size()+" Destination cards"); //Logs it to the game history
		this.games.saveGame(g); //Saves the game state
		return newDestinations;
	}
	
	//Discards destination cards for a player
	public static int discardDestinations(Player p, ArrayList<DestinationCard> cards){
		return getInstance().discardDestinationsInner(p,cards);
	}
	
	private int discardDestinationsInner(Player p, ArrayList<DestinationCard> cards){
		Game g = this.getGameByPlayerInner(p); //Gets the game
		int discarded = 0;
		
		for(int i = 0; i < cards.size(); i++){
			g.getDestinationDeck().addLast(cards.get(i)); //Discards them by putting them on bottom of deck
			discarded++;
		}
		
		g.getHistory().add(p.getUsername()+" discarded "+discarded+" Destination cards"); //Logs it to the game history
		g.getPlayer(p).getDestinations().removeAll(cards);
		this.games.saveGame(g); //Saves the game
		return discarded;
	}
	
	//Adds chat to internal game log
	public static void addChat(Player p, String message){
		getInstance().addChatInner(p,message);
	}
	
	private void addChatInner(Player p, String message){
		Game g = this.getGameByPlayerInner(p); //Gets the game
		g.getChat().add(p.getUsername()+": "+message); //Adds the chat to the game log
		this.games.saveGame(g); //Saves it
	}
	
	//Gets a game by player
	public static Game getGameByPlayer(Player p){
		return getInstance().getGameByPlayerInner(p);
	}
	
	private Game getGameByPlayerInner(Player player){
		for(Game g : this.games.getGames()){
			if(g.getPlayers().contains(player)){
				return g;
			}
		}
		return null;
	}
}
