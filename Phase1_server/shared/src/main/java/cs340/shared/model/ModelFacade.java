package cs340.shared.model;

import java.util.ArrayList;

public class ModelFacade {
	//Singleton
	private static ModelFacade instance;

	private ModelFacade() {}

	public static ModelFacade getInstance(){
		if(instance == null){
			instance = new ModelFacade();
		}
		return instance;
	}

	/* Methods */
	//Gets the list of players from the server model
	public static ArrayList<Player> getPlayers(){
		return cs340.shared.model.ServerModel.getPlayers();
	}

	//Checks to see if the player object matches a player in the db
	//If true, returns the player's auth token
	public static String playerExists(Player player){
		ArrayList<Player> playerList = ServerModel.getPlayers();
		String token = "";

		for(Player p : playerList){ //Iterates through players
			if(p.getUsername().equals(player.getUsername()) && p.getPassword().equals(player.getPassword())){ //If a player with matching username and password is found
				token = p.getAuthToken(); //Say they exist
				break; //Stop the loop
			}
		}

		return token;
	}

	//Gets the player with the matching auth token
	public static Player getPlayer(String auth){
		ArrayList<Player> playerList = ServerModel.getPlayers();
		String token = "";

		for(Player p : playerList){ //Iterates through players
			if(p.getAuthToken().equals(auth)){ //If a player with matching auth token
				return p;
			}
		}

		return null;
	}
}
