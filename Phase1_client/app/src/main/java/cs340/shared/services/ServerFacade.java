package cs340.shared.services;

import cs340.shared.interfaces.IServer;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.JoinGameRequest;
import cs340.shared.requests.SignInRequest;
import cs340.shared.requests.StartGameRequest;

public class ServerFacade implements IServer {

	//Attempts to create a game
	//@pre request is not null
	//
	//@post game is created with details contained in request OR error is sent
	@Override
	public void createGame(Object request) {
		CreateGameRequest createGameRequst = (CreateGameRequest)request;
		
		//Create Game logic
		
	}

	//Attempts to join a game
	//@pre request is not null
	//
	//@post game detailed in request is joined OR error is sent
	@Override
	public void joinGame(Object request) {
		JoinGameRequest joinGameRequest= (JoinGameRequest)request;
		
		//Join Game logic
	}

	//Attempts to login a user
	//@pre request is not null
	//
	//@post user detailed in request is logged in OR error is sent
	@Override
	public void login(Object request) {
		SignInRequest loginRequest = (SignInRequest)request;
		
		//Login logic -- DO NOW
	}

	//Attempts to register a user
	//@pre request is not null
	//
	//@post user detailed in register is registered OR error is sent
	@Override
	public void register(Object request) {
		SignInRequest registerRequest = (SignInRequest)request;
		
		//Register logic -- DO NOW
	}

	//Attempts to start a game
	//@pre request is not null
	//
	//@post game specified in request is started OR error is sent
	@Override
	public void startGame(Object request) {
		StartGameRequest startGameRequest = (StartGameRequest)request;
		
		//Start game logic
	}

}
