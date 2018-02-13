package cs340.client.communication;

import cs340.shared.interfaces.ICommand;
import cs340.shared.interfaces.IServer;
import cs340.shared.message.Message;
import cs340.shared.requests.*;

import com.google.gson.Gson;

public class ServerProxy implements IServer{
	private static Gson gson = new Gson();

	@Override
	public void createGame(Object request) {
		CreateGameRequest createRequest = (CreateGameRequest) request;
		Message message = new Message(((CreateGameRequest) request).getPlayer().getAuthToken(), (ICommand)request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void joinGame(Object request) {
		JoinGameRequest createRequest = (JoinGameRequest) request;
		Message message = new Message(((JoinGameRequest) request).getPlayer().getAuthToken(), (ICommand)request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void login(Object request) {
		SignInRequest createRequest = (SignInRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void register(Object request) {
		SignInRequest createRequest = (SignInRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void startGame(Object request) {
		StartGameRequest createRequest = (StartGameRequest) request;
		Message message = new Message(request.getPlayer().getAuthToken(), request);
		ClientCommunicator.getInstance().sendMessage(message);
	}
}
