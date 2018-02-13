package cs340.client.services;

import java.util.ArrayList;

import cs340.shared.interfaces.IClient;
import cs340.shared.model.Game;
import cs340.shared.model.Player;

/**
 * Executes functions based on ClientCommands processed by CommandProcessor coming from the server.
 * I need things to fill this with.
 */
public class ClientFacade implements IClient {
	public void createGame(Object game) {
		CreateGameService.onGameCreated((Game) game);
	}

	public void joinGame(Object game) {
		JoinGameService.onGameJoined((Game) game);
	}

	public void login(Object player) {
		LoginService.onLogin((Player) player);
	}

	public void register(Object player) {
		RegisterService.onRegister((Player) player);
	}

	public void startGame(Object request) {
		StartGameService.onGameStarted();
	}
	
	public void updateGameList(Object gameList) {
		UpdateGameListService.onUpdateGameList((ArrayList<Game>) gameList);
	}

	public void error(Object error) {
		ErrorService.onError((String) error);
	}
}
