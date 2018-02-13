package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.requests.StartGameRequest;
import cs340.client.communication.ServerProxy;

public class StartGameService {
	private static ServerProxy proxy = new ServerProxy();

	public static void onGameStarted() {
		ClientModel.getInstance().startGame();
	}

	public static void startGame(Player player) {
		proxy.startGame(new StartGameRequest(player));
	}
}
