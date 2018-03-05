package cs340.shared.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;

public class Game {
	/* Fields */
	private String gameName;
	private int gameID;
	private ArrayList<Player> players;
	private int capacity;
	private int playerCount;
	private HashMap<String,String> colors;
	private boolean started;
	private int turn; //Index of player who is currently taking a turn
	private ArrayList<Route> unclaimedRoutes;
	private ArrayList<TrainCard> trainDeck;
	private ArrayList<TrainCard> trainFaceup;
	private ArrayList<TrainCard> trainDiscard;
	private Deque<DestinationCard> destinationDeck;
	private Map gameMap;
	private ArrayList<String> history;
	private ArrayList<String> chat;
	
	/* "Maybe Later" Fields */
	//private Map gameMap;
	//private TrainCard[7] shownCards;
	//private Stack<TrainCard> deck;
	
	public Game(String gameName, ArrayList<Player> players, int capacity) {
		this.gameName = gameName;
		this.players = players;
		this.capacity = capacity;
		this.playerCount = 1;
		this.gameID = ServerModel.getNextGameID();
		this.colors = new HashMap<String,String>();
		this.started = false;
		this.unclaimedRoutes = GameStarter.createRouteList();
		this.trainDeck = GameStarter.createTrainDeck();
		this.trainFaceup = new ArrayList<TrainCard>();
		this.trainDiscard = new ArrayList<TrainCard>();
		this.destinationDeck = GameStarter.createDestinationDeck();
		this.gameMap = new Map(); //Update when class is updated
		this.history = new ArrayList<String>();
		this.chat = new ArrayList<String>();
	}
		
	/* Methods */
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
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public boolean addPlayer(Player p, String color){
		if(this.playerCount < this.capacity){
			if(this.getRemainingColors().contains(color)){
				this.players.add(p);
				this.playerCount++;
				this.colors.put(p.getUsername(), color);
				return true;
			}
		}
		return false;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	public HashMap<String,String> getColors(){
		return this.colors;
	}
	
	public void setColor(String username, String color){
		this.colors.put(username, color);
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public ArrayList<Route> getUnclaimedRoutes() {
		return unclaimedRoutes;
	}

	public void setUnclaimedRoutes(ArrayList<Route> unclaimedRoutes) {
		this.unclaimedRoutes = unclaimedRoutes;
	}

	public ArrayList<TrainCard> getTrainDeck() {
		return trainDeck;
	}

	public void setTrainDeck(ArrayList<TrainCard> trainDeck) {
		this.trainDeck = trainDeck;
	}

	public ArrayList<TrainCard> getTrainFaceup() {
		return trainFaceup;
	}

	public void setTrainFaceup(ArrayList<TrainCard> trainFaceup) {
		this.trainFaceup = trainFaceup;
	}

	public ArrayList<TrainCard> getTrainDiscard() {
		return trainDiscard;
	}

	public void setTrainDiscard(ArrayList<TrainCard> trainDiscard) {
		this.trainDiscard = trainDiscard;
	}

	public Deque<DestinationCard> getDestinationDeck() {
		return destinationDeck;
	}

	public void setDestinationDeck(Deque<DestinationCard> destinationDeck) {
		this.destinationDeck = destinationDeck;
	}

	public Map getGameMap() {
		return gameMap;
	}

	public void setGameMap(Map gameMap) {
		this.gameMap = gameMap;
	}

	public void setColors(HashMap<String, String> colors) {
		this.colors = colors;
	}
	
	public ArrayList<String> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<String> history) {
		this.history = history;
	}

	public ArrayList<String> getChat() {
		return chat;
	}

	public void setChat(ArrayList<String> chat) {
		this.chat = chat;
	}
	
	//Gets a player in the game
	public Player getPlayer(String auth){
		for(Player p : this.players){
			if(p.getUsername().equals(auth)){
				return p;
			}
		}
		return null; //Not found
	}
	
	//Gets a player in the game
	public Player getPlayer(Player player){
		for(Player p : this.players){
			if(p.getUsername().equals(player.getUsername()) && p.getAuthToken().equals(player.getAuthToken())){
				return p;
			}
		}
		return null; //Not found
	}
	
	//Returns the list of player auth tokens for this game
	public ArrayList<String> getAuths(){
		ArrayList<String> auths = new ArrayList<String>();
		for(Player p : this.players){
			auths.add(p.getAuthToken());
		}
		return auths;
	}

	//Draws a card from the top of the deck and removes it from the deck arraylist
	public TrainCard drawTrainCard(){
		if(this.trainDeck.size() == 0){ //If the deck is empty
			this.shuffleTrainCards(); //Shuffle it
		}
		
		TrainCard card = this.trainDeck.get(0); //Gets the top card of the deck
		this.trainDeck.remove(0); //Removes it from the deck
		return card; //And returns it
	}
	
	//Draws a card from the top of the deck and removes it from the deck arraylist
	public DestinationCard drawDestination(){
		if(this.destinationDeck.size() == 0){ //If the deck is empty
			return null; //We can't draw any
		}
			
		return this.destinationDeck.pop(); //Returns the top card from the destination deck and removes it from the deck
	}

	//This looks really ugly and I think I need to revamp it
	//But I'm tired and not sure how, and it works, so that's not my top priority right now
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
	
	//Shuffles the discard, then adds it to the bottom of the deck
	public void shuffleTrainCards(){
		Collections.shuffle(this.trainDiscard);
		this.trainDeck.addAll(this.trainDiscard);
		this.trainDiscard.clear();
	}
	
	//Starts the game
	public void startGame(){
		this.started = true;
		for(Player p : this.players){ //Deals starting hands
			for(int i = 0; i < 4; i++){ //Train cards
				p.getCards().add(this.drawTrainCard());
			}
			
			for(int i = 0; i < 3; i++){ //Destination cards
				DestinationCard card = this.drawDestination();
				if(card != null){ //If successful
					p.getDestinations().add(card); //Add it to the list
				}else{ //If there aren't any cards left
					break; //Break the loop
				}
			}
		}
		
		//Sets up faceup cards
		for(int i =0; i < 5; i++){
			this.trainFaceup.add(this.drawTrainCard());
		}
	}
}
