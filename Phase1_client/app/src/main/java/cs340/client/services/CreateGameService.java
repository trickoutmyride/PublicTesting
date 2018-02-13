package cs340.client.services;

import cs340.shared.requests.CreateGameRequest;
import cs340.client.communication.ServerProxy;

public class CreateGameService {
	private static final CreateGameService singleton = new CreateGameService();

	public void createGame(CreateGameRequest request) {
		singleton.createGameInner(request);
	}

	private void createGameInner(CreateGameRequest request) {
		//ServerProxy.singleton.createGame(request);
	}
}