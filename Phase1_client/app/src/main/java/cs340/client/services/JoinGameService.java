package cs340.client.services;

import cs340.shared.requests.JoinGameRequest;
import cs340.client.communication.ServerProxy;

public class JoinGameService {
	private static final JoinGameService singleton = new JoinGameService();

	public void joinGame(JoinGameRequest request) {
		singleton.joinGameInner(request);
	}

	private void joinGameInner(JoinGameRequest request) {
		ServerProxy.singleton.joinGame(request);
	}
}
