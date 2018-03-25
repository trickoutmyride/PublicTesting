package cs340.client.services;

import java.util.ArrayList;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.shared.requests.DiscardDestinationRequest;
import cs340.shared.requests.DrawDestinationRequest;
import cs340.shared.requests.DrawFaceupRequest;
import cs340.shared.requests.DrawTrainCardRequest;

public class DeckService {
	private static ServerProxy proxy = new ServerProxy();

	public static void drawDestination(int gameID, Player player) {
		proxy.drawDestination(new DrawDestinationRequest(gameID, player));
	}

	public static void discardDestination(int gameID, ArrayList<DestinationCard> cards, Player player, boolean isDuringGame) {
		proxy.discardDestination(new DiscardDestinationRequest(gameID, cards, player), isDuringGame);
	}

	public static void drawTrainCard(Player player) {
		proxy.drawTrainCard(new DrawTrainCardRequest(player));
	}

	public static void drawFaceup(Player player, int index) {
		proxy.drawFaceupCard(new DrawFaceupRequest(player, index));
	}

	public static void onDrawTrainCards(int index, TrainCard drawnCard, TrainCard newCard, Player player){
		ClientModel.getInstance().updateFaceUpDeck(index, drawnCard, newCard, player);
	}

	public static void onDrawDeckCard(Player player){
		ClientModel.getInstance().updateHandTrainCards(player);
	}

	public static void onDrawDestinationCards(ArrayList<DestinationCard> cards) {
		ClientModel.getInstance().newDestinationCards(cards);
	}

	public static void onDiscardDestinationCards(Player player) {
		System.out.println("DeckService onDiscardDestinationCards()");
		ClientModel.getInstance().updatePlayerDestinationCards(player);
	}
}
