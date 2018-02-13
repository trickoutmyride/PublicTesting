package cs340.client.services;

import com.google.gson.Gson;

import java.util.ArrayList;

import cs340.shared.interfaces.IClient;
import cs340.shared.model.Game;
import cs340.shared.model.Player;

/**
 * Executes functions based on ClientCommands processed by CommandProcessor coming from the server.
 * I need things to fill this with.
 */
public class ClientFacade implements IClient {
	private static Gson gson = new Gson();
	public void createGame(Object game) {
		System.out.println("ClientFacade: createGame()");
		CreateGameService.onGameCreated((Game) game);
	}

	public void joinGame(Object gameJson) {
		System.out.println("ClientFacade: joinGame()");
		Game game = gson.fromJson((String)gameJson, Game.class);
		JoinGameService.onGameJoined((Game) game);
	}

	public void login(Object playerJson) {
		System.out.println("ClientFacade: login()");
		Player player = gson.fromJson((String)playerJson, Player.class);
		LoginService.onLogin((Player) player);
	}

	public void register(Object playerJson) {
		System.out.println("ClientFacade: register()");
		Player player = gson.fromJson((String)playerJson, Player.class);
		RegisterService.onRegister((Player) player);
	}

	public void startGame() {
		System.out.println("ClientFacade: startGame()");
		StartGameService.onGameStarted();
	}

	public void updateGameList(Object gameListJson) {
		System.out.println("ClientFacade: updateGameList()");
		ArrayList<Game> gameList = gson.fromJson((String)gameListJson, ArrayList.class);
		UpdateGameListService.onUpdateGameList((ArrayList<Game>) gameList);
	}

	public void error(Object error) {
		System.out.println("ClientFacade: error()");
		ErrorService.onError((String) error);
	}
}