package cs340.client.services;

import cs340.shared.requests.StartGameRequest;
import cs340.client.communication.ServerProxy;

public class UpdateGameListService {
	private static final UpdateGameListService singleton = new UpdateGameListService();

	public static void UpdateGameList(StartGameRequest request) {
		singleton.UpdateGameListInner(request);
	}

	private void startGameInner(StartGameRequest request) {
		ServerProxy.singleton.UpdateGameList(request);
	}
}
