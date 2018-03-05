package cs340.shared.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

//Class of static methods used to instantiate decks for games
public class GameStarter {
	
	//Creates the train deck
	public static ArrayList<TrainCard> createTrainDeck(){
		ArrayList<TrainCard> deckList = new ArrayList<TrainCard>();
		
		//Adds 12 of each color of card
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 12; j++){
				switch(i){
				case 0: deckList.add(new TrainCard("purple"));
				break;
				case 1: deckList.add(new TrainCard("white"));
				break;
				case 2: deckList.add(new TrainCard("blue"));
				break;
				case 3: deckList.add(new TrainCard("yellow"));
				break;
				case 4: deckList.add(new TrainCard("orange"));
				break;
				case 5: deckList.add(new TrainCard("black"));
				break;
				case 6: deckList.add(new TrainCard("red"));
				break;
				case 7: deckList.add(new TrainCard("green"));
				break;
				case 8: deckList.add(new TrainCard("wild"));
				break;
				}
			}
		}
		
		deckList.add(new TrainCard("wild")); //14 Locomotives in deck
		deckList.add(new TrainCard("wild"));
		
		Collections.shuffle(deckList); //Shuffles the deck
		return deckList; //Returns the deck
	}
	
	//Creates the destination card deck
	public static Deque<DestinationCard> createDestinationDeck(){
		ArrayList<DestinationCard> deck = new ArrayList<DestinationCard>();
		
		deck.add(new DestinationCard("Los Angeles","New York",21));
		deck.add(new DestinationCard("Duluth","Houston",8));
		deck.add(new DestinationCard("Sault Ste Marie","Nashville",8));
		deck.add(new DestinationCard("New York","Atlanta",6));
		deck.add(new DestinationCard("Portland","Nashville",17));
		deck.add(new DestinationCard("Vancouver","Montreal",20));
		deck.add(new DestinationCard("Duluth","El Paso",10));
		deck.add(new DestinationCard("Toronto","Miami",10));
		deck.add(new DestinationCard("Portland","Phoenix",11));
		deck.add(new DestinationCard("Dallas","New York",11));
		deck.add(new DestinationCard("Calgary","Salt Lake City",7));
		deck.add(new DestinationCard("Calgary","Phoenix",13));
		deck.add(new DestinationCard("Los Angeles","Miami",20));
		deck.add(new DestinationCard("Winnipeg","Little Rock",11));
		deck.add(new DestinationCard("San Francisco","Atlanta",17));
		deck.add(new DestinationCard("Kansas City","Houston",5));
		deck.add(new DestinationCard("Los Angeles","Chicago",16));
		deck.add(new DestinationCard("Denver","Pittsburgh",11));
		deck.add(new DestinationCard("Chicago","Santa Fe",9));
		deck.add(new DestinationCard("Vancouver","Santa Fe",13));
		deck.add(new DestinationCard("Boston","Miami",12));
		deck.add(new DestinationCard("Chicago","New Orleans",7));
		deck.add(new DestinationCard("Montreal","Atlanta",9));
		deck.add(new DestinationCard("Seattle","New York",22));
		deck.add(new DestinationCard("Denver","El Paso",4));
		deck.add(new DestinationCard("Helena","Los Angeles",8));
		deck.add(new DestinationCard("Winnipeg","Houston",12));
		deck.add(new DestinationCard("Montreal","New Orleans",13));
		deck.add(new DestinationCard("Sault Ste Marie","Oklahoma City",9));
		deck.add(new DestinationCard("Seattle","Los Angeles",9));
		
		Collections.shuffle(deck);
		
		Deque<DestinationCard> destinations = new LinkedList<DestinationCard>();
		destinations.addAll(deck);
		return destinations;
	}
	
	//Creates the list of routes
	public static ArrayList<Route> createRouteList(){
		ArrayList<Route> routes = new ArrayList<Route>();
		
		routes.add(new Route("Vancouver","Seattle",2,"grey"));
		routes.add(new Route("Vancouver","Seattle",2,"grey"));
		routes.add(new Route("Vancouver","Calgary",3,"grey"));
		routes.add(new Route("Seattle","Calgary",4,"grey"));
		routes.add(new Route("Seattle","Portland",2,"grey"));
		routes.add(new Route("Seattle","Portland",2,"grey"));
		routes.add(new Route("Seattle","Helena",6,"yellow"));
		routes.add(new Route("Portland","Salt Lake City",6,"blue"));
		routes.add(new Route("Portland","San Fancsico",5,"green"));
		routes.add(new Route("Portland","San Francisco",5,"pink"));
		routes.add(new Route("San Francisco","Salt Lake City",5,"orange"));
		routes.add(new Route("San Francisco","Salt Lake City",5,"pink"));
		routes.add(new Route("San Francisco","Los Angeles",3,"yellow"));
		routes.add(new Route("San Francisco","Los Angeles",3,"pink"));
		routes.add(new Route("Los Angeles","Las Vegas",2,"grey"));
		routes.add(new Route("Los Angeles","Phoenix",3,"grey"));
		routes.add(new Route("Los Angeles","El Paso",6,"black"));
		routes.add(new Route("Calgary","Winnipeg",6,"white"));
		routes.add(new Route("Calgary","Helena",4,"grey"));
		routes.add(new Route("Helena","Salt Lake City",3,"pink"));
		routes.add(new Route("Helena","Winnipeg",4,"blue"));
		routes.add(new Route("Helena","Duluth",6,"orange"));
		routes.add(new Route("Helena","Omaha",5,"red"));
		routes.add(new Route("Helena","Denver",4,"green"));
		routes.add(new Route("Salt Lake City","Denver",3,"red"));
		routes.add(new Route("Salt Lake City","Denver",3,"yellow"));
		routes.add(new Route("Salt Lake City","Las Vegas",3,"orange"));
		routes.add(new Route("Phoenix","Denver",5,"white"));
		routes.add(new Route("Phoenix","Santa Fe",3,"grey"));
		routes.add(new Route("Phoenix","El Paso",3,"grey"));
		routes.add(new Route("Denver","Omaha",4,"pink"));
		routes.add(new Route("Denver","Kansas City",4,"black"));
		routes.add(new Route("Denver","Kansas City",4,"orange"));
		routes.add(new Route("Denver","Oklahoma City",4,"red"));
		routes.add(new Route("Denver","Santa Fe",2,"grey"));
		routes.add(new Route("Santa Fe","El Paso",2,"grey"));
		routes.add(new Route("Santa Fe","Oklahoma City",3,"blue"));
		routes.add(new Route("El Paso","Oklahoma City",5,"yellow"));
		routes.add(new Route("El Paso","Dallas",4,"red"));
		routes.add(new Route("El Paso","Houston",6,"green"));
		routes.add(new Route("Winnipeg","Sault St Marie",6,"grey"));
		routes.add(new Route("Winnipeg","Duluth",4,"black"));
		routes.add(new Route("Duluth","Saul St Marie",3,"grey"));
		routes.add(new Route("Duluth","Toronto",6,"pink"));
		routes.add(new Route("Duluth","Chicago",3,"red"));
		routes.add(new Route("Duluth","Omaha",2,"grey"));
		routes.add(new Route("Duluth","Omaha",2,"grey"));
		routes.add(new Route("Omaha","Chicago",4,"blue"));
		routes.add(new Route("Omaha","Kansas City",2,"grey"));
		routes.add(new Route("Omaha","Kansas City",2,"grey"));
		routes.add(new Route("Kansas City","Saint Louis",2,"blue"));
		routes.add(new Route("Kansas City","Saint Louis",2,"pink"));
		routes.add(new Route("Kansas City","Oklahoma City",2,"grey"));
		routes.add(new Route("Kansas City","Oklahoma City",2,"grey"));
		routes.add(new Route("Oklahoma City","Little Rock",2,"grey"));
		routes.add(new Route("Oklahoma City","Dallas",2,"grey"));
		routes.add(new Route("Oklahoma City","Dallas",2,"grey"));
		routes.add(new Route("Dallas","Little Rock",2,"grey"));
		routes.add(new Route("Dallas","Houston",1,"grey"));
		routes.add(new Route("Dallas","Houston",1,"grey"));
		routes.add(new Route("Saul St Marie","Montreal",5,"black"));
		routes.add(new Route("Saul St Marie","Toronto",2,"grey"));
		routes.add(new Route("Chicago","Toronto",4,"white"));
		routes.add(new Route("Chicago","Pittsburgh",3,"orange"));
		routes.add(new Route("Chicago","Pittsburgh",3,"black"));
		routes.add(new Route("Chicago","Saint Louis",2,"green"));
		routes.add(new Route("Chicago","Saint Louis",2,"white"));
		routes.add(new Route("Saint Louis","Pittsburgh",5,"green"));
		routes.add(new Route("Saint Louis","Nashville",2,"grey"));
		routes.add(new Route("Saint Louis","Little Rock",2,"grey"));
		routes.add(new Route("Little Rock","Nashville",3,"white"));
		routes.add(new Route("Little Rock","New Orleans",3,"green"));
		routes.add(new Route("New Orleans","Atlanta",4,"yellow"));
		routes.add(new Route("New Orleans","Atlanta",4,"orange"));
		routes.add(new Route("New Orleans","Miami",6,"red"));		
		routes.add(new Route("Toronto","Montreal",3,"grey"));
		routes.add(new Route("Toronto","Pittsburgh",2,"grey"));
		routes.add(new Route("Pittsburgh","New York",2,"white"));
		routes.add(new Route("Pittsburgh","New York",2,"green"));
		routes.add(new Route("Pittsburgh","Washington",2,"grey"));
		routes.add(new Route("Pittsburgh","Raleigh",2,"grey"));
		routes.add(new Route("Nashville","Pittsburgh",4,"yellow"));
		routes.add(new Route("Nashville","Raleigh",3,"black"));
		routes.add(new Route("Nashville","Atlanta",1,"grey"));
		routes.add(new Route("Atlanta","Raleigh",2,"grey"));
		routes.add(new Route("Atlanta","Raleigh",2,"grey"));
		routes.add(new Route("Atlanta","Charleston",2,"grey"));
		routes.add(new Route("Atlanta","Miami",5,"blue"));
		routes.add(new Route("Montreal","Boston",2,"grey"));
		routes.add(new Route("Montreal","Boston",2,"grey"));
		routes.add(new Route("Montreal","New York",3,"blue"));
		routes.add(new Route("Boston","New York",2,"yellow"));
		routes.add(new Route("Boston","New York",2,"red"));
		routes.add(new Route("New York","Washington",2,"orange"));
		routes.add(new Route("New York","Washington",2,"black"));
		routes.add(new Route("Raleigh","Washington",2,"grey"));
		routes.add(new Route("Raleigh","Washington",2,"grey"));
		routes.add(new Route("Raleigh","Charleston",2,"grey"));
		routes.add(new Route("Charleston","Miami",4,"pink"));
		
		return routes;
	}
}
