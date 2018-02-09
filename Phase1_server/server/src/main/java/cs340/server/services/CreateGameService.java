package cs340.server.services;

import cs340.shared.requests.CreateGameRequest;

public class CreateGameService {
	private static CreateGameService instance;
	
	private CreateGameService (){}
	
	public static CreateGameService getInstance(){
		if(instance == null){
			instance = new CreateGameService();
		}
		
		return instance;
	}
	
	public static boolean createGame(CreateGameRequest request){
		return getInstance().createGameInner(request);
	}
	
	private boolean createGameInner(CreateGameRequest request){
		return false;
	}
}
