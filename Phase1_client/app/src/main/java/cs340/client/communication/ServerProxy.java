package cs340.client.communication;

import cs340.shared.interfaces.IServer;

public class ServerProxy implements IServer{
	public static final ServerProxy singleton = new ServerProxy();

	@Override
	public void createGame(Object request){

	}
	@Override
	public void joinGame(Object request){

	}
	@Override
	public void login(Object request){

	}
	@Override
	public void register(Object request){

	}
	@Override
	public void startGame(Object request){

	}
/*
	private void createGameInner(Object request) {
		
	}

	private void joinGameInner(Object request) {
		
	}

	private void loginInner(Object request) {
		
	}

	private void registerInner(Object request) {
		
	}

	private void startGameInner(Object request) {
		
	}
*/
}
