package cs340.client.services;

import cs340.shared.requests.StartGameRequest;
import cs340.client.communication.ServerProxy;

public class StartGameService {
	private static final StartGameService singleton = new StartGameService();

	public static void startGame(StartGameRequest request) {
		singleton.startGameInner(request);
	}

	private void startGameInner(StartGameRequest request) {
		ServerProxy.singleton.startGame(request);
	}
}
