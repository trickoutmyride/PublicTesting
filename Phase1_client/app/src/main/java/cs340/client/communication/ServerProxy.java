package cs340.client.communication;

import com.google.gson.Gson;

import cs340.client.command.CommandManager;
import cs340.shared.command.ServerCommand;
import cs340.shared.interfaces.IServer;
import cs340.shared.message.ServerMessage;
import cs340.shared.requests.ChatRequest;
import cs340.shared.requests.ClaimRouteRequest;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.DrawDestinationRequest;
import cs340.shared.requests.DrawTrainCardRequest;
import cs340.shared.requests.JoinGameRequest;
import cs340.shared.requests.SignInRequest;
import cs340.shared.requests.StartGameRequest;

public class ServerProxy implements IServer{
	private static Gson gson = new Gson();

	@Override
	public void createGame(Object request) {
		CreateGameRequest createRequest = (CreateGameRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("createGame", request);
		ServerMessage message = new ServerMessage(createRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	@Override
	public void joinGame(Object request) {
		JoinGameRequest joinRequest = (JoinGameRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("joinGame", request);
		ServerMessage message = new ServerMessage(joinRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void login(Object request) {
		SignInRequest loginRequest = (SignInRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("login", request);
		ServerMessage message = new ServerMessage(loginRequest.getUsername(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void register(Object request) {
		SignInRequest registerRequest = (SignInRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("register", request);
		ServerMessage message = new ServerMessage(((SignInRequest) request).getUsername(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	@Override
	public void startGame(Object request) {
		StartGameRequest startRequest = (StartGameRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("startGame", request);
		ServerMessage message = new ServerMessage(startRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void claimRoute(Object request) {
		ClaimRouteRequest claimRequest = (ClaimRouteRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("claimRoute", request);
		ServerMessage message = new ServerMessage(claimRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void drawDestination(Object request) {
		DrawDestinationRequest drawRequest = (DrawDestinationRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("drawDestination", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void discardDestination(Object request) {
		DrawDestinationRequest drawRequest = (DrawDestinationRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("drawDestination", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void drawTrainCard (Object request) {
		DrawTrainCardRequest drawRequest = (DrawTrainCardRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("drawTrain", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void chat(Object request) {
		ChatRequest chatRequest = (ChatRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("chat", request);
		ServerMessage message = new ServerMessage(chatRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
}