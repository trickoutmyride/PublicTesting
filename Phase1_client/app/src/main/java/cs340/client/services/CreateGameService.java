package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.CreateGameRequest;
import cs340.client.communication.ServerProxy;

public class CreateGameService {
	private static ServerProxy proxy = new ServerProxy();

	public static void createGame(String name, Player player, int capacity, String color) {
		proxy.createGame(new CreateGameRequest(name, player, capacity, color));
	}

	public static void onGameCreated(Game game) {
		ClientModel.getInstance().setCurrentGame(game);
	}
}
