package cs340.client.services;

import java.util.ArrayList;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.shared.requests.DiscardDestinationRequest;
import cs340.shared.requests.DrawDestinationRequest;
import cs340.shared.requests.DrawTrainCardRequest;

public class DeckService {
	private static ServerProxy proxy = new ServerProxy();

	public static void drawDestination(int gameID, Player player) {
		proxy.drawDestination(new DrawDestinationRequest(gameID, player));
	}

	public static void discardDestination(int gameID, ArrayList<DestinationCard> cards, Player player) {
		proxy.discardDestination(new DiscardDestinationRequest(gameID, cards, player));
	}

	public static void drawTrainCard(int gameID, TrainCard card, Player player) {
		proxy.drawTrainCard(new DrawTrainCardRequest(gameID, card, player));
	}

	public static void onDrawTrainCards(int index, TrainCard drawnCard, ArrayList<TrainCard> cards){
		ClientModel.getInstance().updateFaceUpDeck(index, drawnCard, cards);
	}

	public static void onDrawDeckCard(ArrayList<TrainCard> playerCards){
		ClientModel.getInstance().updateHandTrainCards(playerCards);
	}

	public static void onDrawDestinationCards(ArrayList<DestinationCard> cards) {
		ClientModel.getInstance().newDestinationCards(cards);
	}
}
