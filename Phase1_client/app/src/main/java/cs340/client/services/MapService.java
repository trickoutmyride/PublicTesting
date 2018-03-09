package cs340.client.services;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.model.Route;
import cs340.shared.requests.ClaimRouteRequest;


public class MapService {
	private static ServerProxy proxy = new ServerProxy();

	public static void claimRoute(Player player, String start, String end, String color) {
		ClaimRouteRequest request = new ClaimRouteRequest(player, new Route (start, end, -1, color));
		proxy.claimRoute(request);
	}
	public static void onRouteClaimed(Player player, String start, String end) {
		ClientModel.getInstance().getCurrentGame().getGameMap().onRouteClaimed(player, start, end);
	}
}
