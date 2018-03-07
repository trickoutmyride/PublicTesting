package cs340.client.services;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.StartGameRequest;

public class StartGameService {
	private static ServerProxy proxy = new ServerProxy();

	public static void onGameStarted(Game game) {
		ClientModel.getInstance().startGame(game);
	}

	public static void startGame(Player player) {
		proxy.startGame(new StartGameRequest(player));
	}
}
