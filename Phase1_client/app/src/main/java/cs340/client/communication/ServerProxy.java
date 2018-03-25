package cs340.client.communication;

import com.google.gson.Gson;

import cs340.client.command.CommandManager;
import cs340.client.states.MyTurnState;
import cs340.client.states.NotMyTurnState;
import cs340.client.states.TurnState;
import cs340.shared.command.ServerCommand;
import cs340.shared.interfaces.IServer;
import cs340.shared.message.ServerMessage;
import cs340.shared.requests.ChatRequest;
import cs340.shared.requests.ClaimRouteRequest;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.DiscardDestinationRequest;
import cs340.shared.requests.DrawDestinationRequest;
import cs340.shared.requests.DrawFaceupRequest;
import cs340.shared.requests.DrawTrainCardRequest;
import cs340.shared.requests.EndTurnRequest;
import cs340.shared.requests.JoinGameRequest;
import cs340.shared.requests.SignInRequest;
import cs340.shared.requests.StartGameRequest;

public class ServerProxy implements IServer {
	private static Gson gson = new Gson();
	private static TurnState turnState;

	/**
	 * Called by the TurnService when an EndTurn command is received informing us that it is now our turn.
	 * @param newTurnState
	 */
	public static void setTurnState(TurnState newTurnState) {
		turnState = newTurnState;
	}

	@Override
	public void createGame(Object request) {
		turnState = new MyTurnState();
		CreateGameRequest createRequest = (CreateGameRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("createGame", request);
		ServerMessage message = new ServerMessage(createRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	@Override
	public void joinGame(Object request) {
		turnState = new NotMyTurnState();
		JoinGameRequest joinRequest = (JoinGameRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("joinGame", request);
		ServerMessage message = new ServerMessage(joinRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
	public void login(Object request, String address) {
		ClientCommunicator.initialize(address);
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
		turnState = turnState.claimRoute((claimRequest).getPlayer());
		if (!turnState.isSuccess()) return;

		ServerCommand command = CommandManager.getInstance().makeCommand("claimRoute", request);
		ServerMessage message = new ServerMessage(claimRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	/**
	 * @param isDuringGame if true, change turnState, false for the first set you draw at start of game.
	 */
	public void drawDestination(Object request) {
		DrawDestinationRequest drawRequest = (DrawDestinationRequest) request;
		turnState = turnState.drawDestination((drawRequest).getPlayer());
		if (!turnState.isSuccess()) return;

		ServerCommand command = CommandManager.getInstance().makeCommand("drawDestination", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	/**
	 * @param isDuringGame if true, change turnState, false for the first set you draw at start of game.
	 */
	public void discardDestination(Object request, boolean isDuringGame) {
		DiscardDestinationRequest discardRequest = (DiscardDestinationRequest) request;
		if (isDuringGame) {
			turnState = turnState.drawDestination((discardRequest).getPlayer());
			if (!turnState.isSuccess()) return;
		}

		ServerCommand command = CommandManager.getInstance().makeCommand("discardDestination", request);
		ServerMessage message = new ServerMessage(discardRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void drawTrainCard (Object request) {
		DrawTrainCardRequest drawRequest = (DrawTrainCardRequest) request;
		turnState = turnState.drawTrainCard((drawRequest).getPlayer());
		if (!turnState.isSuccess()) return;

		ServerCommand command = CommandManager.getInstance().makeCommand("drawTrainCard", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void drawFaceupCard (Object request) {
		DrawFaceupRequest drawRequest = (DrawFaceupRequest) request;
		turnState = turnState.drawFaceupCard((drawRequest).getPlayer(), drawRequest);
		if (!turnState.isSuccess()) return;

		ServerCommand command = CommandManager.getInstance().makeCommand("drawFaceup", request);
		ServerMessage message = new ServerMessage(drawRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void endTurn(Object request) {
		EndTurnRequest endRequest = (EndTurnRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("endTurn", request);
		ServerMessage message = new ServerMessage(endRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}

	public void chat(Object request) {
		ChatRequest chatRequest = (ChatRequest) request;
		ServerCommand command = CommandManager.getInstance().makeCommand("chat", request);
		ServerMessage message = new ServerMessage(chatRequest.getPlayer().getAuthToken(), command);
		ClientCommunicator.getInstance().sendMessage(message);
	}
}