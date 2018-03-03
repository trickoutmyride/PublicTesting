package cs340.client.services;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.Player;
import cs340.shared.requests.ClaimRouteRequest;


public class MapService {
	private static ServerProxy proxy = new ServerProxy();

	public static void claimRoute(Player player, String start, String end) {
		proxy.claimRoute(new ClaimRouteRequest(player, start, end));
	}
	public static void onRouteClaimed(Player player, String start, String end) {
		// needs on method from presenter!
	}
}
