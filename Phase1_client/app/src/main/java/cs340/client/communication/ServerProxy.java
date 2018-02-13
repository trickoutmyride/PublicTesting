package cs340.client.communication;

import com.google.gson.Gson;

import cs340.shared.command.ServerCommand;
import cs340.shared.interfaces.ICommand;
import cs340.shared.interfaces.IServer;
import cs340.shared.message.Message;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.JoinGameRequest;
import cs340.shared.requests.SignInRequest;
import cs340.shared.requests.StartGameRequest;

public class ServerProxy implements IServer{
	private static Gson gson = new Gson();

	@Override
	public void createGame(Object request) {
		CreateGameRequest createRequest = (CreateGameRequest) request;
		ICommand command = new ServerCommand("createGame", request);
		Message message = new Message(createRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void joinGame(Object request) {
		JoinGameRequest joinRequest = (JoinGameRequest) request;
		ICommand command = new ServerCommand("joinGame", request);
		Message message = new Message(joinRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void login(Object request) {
		SignInRequest loginRequest = (SignInRequest) request;
		ICommand command = new ServerCommand("login", request);
		Message message = new Message(loginRequest.getUsername(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void register(Object request) {
		SignInRequest registerRequest = (SignInRequest) request;
		ICommand command = new ServerCommand("register", request);
		Message message = new Message(((SignInRequest) request).getUsername(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void startGame(Object request) {
		StartGameRequest startRequest = (StartGameRequest) request;
		ICommand command = new ServerCommand("joinGame", request);
		Message message = new Message(startRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
}
