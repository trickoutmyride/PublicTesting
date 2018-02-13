package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.JoinGameRequest;
import cs340.client.communication.ServerProxy;

public class JoinGameService {
	private static ServerProxy proxy = new ServerProxy();

	public static void joinGame(int gameID, Player player, String color) {
		proxy.joinGame(new JoinGameRequest(gameID, player, color));
	}

	public static void onGameJoined(Game game) {
		ClientModel.getInstance().setCurrentGame(game);
	}
}
