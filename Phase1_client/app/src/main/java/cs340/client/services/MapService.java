package cs340.client.services;

import java.util.ArrayList;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.model.Route;
import cs340.shared.model.TrainCard;
import cs340.shared.requests.ClaimRouteRequest;


public class MapService {
	private static ServerProxy proxy = new ServerProxy();

	public static void claimRoute(Player player, String start, String end, String color, ArrayList<TrainCard> spendCards) {
		ClaimRouteRequest request = new ClaimRouteRequest(player, new Route (start, end, -1, color), spendCards);
		proxy.claimRoute(request);
	}
	public static void onRouteClaimed(String username, Route route) {
		ClientModel model = ClientModel.getInstance();
		ArrayList<String> endpoints = route.getEndpoints();
		ClientModel.getInstance().getCurrentGame().getGameMap().onRouteClaimed(username, endpoints.get(0), endpoints.get(1));
	}
}
