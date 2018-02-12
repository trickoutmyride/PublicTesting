package cs340.client.communication;

import cs340.shared.interfaces.IServer;
import com.google.gson.Gson;

public class ServerProxy implements IServer{
	private static Gson gson = new Gson();

	@Override
	public void createGame(Object request) {
		CreateGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void joinGame(Object request) {
		JoinGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void login(Object request) {
		SignInGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void register(Object request) {
		SignInGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void startGame(Object request) {
		StartGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
}
