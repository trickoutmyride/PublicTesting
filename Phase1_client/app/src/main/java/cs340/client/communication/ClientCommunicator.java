package cs340.client.communication;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import cs340.shared.command.ServerCommand;
import cs340.shared.message.Message;
import cs340.shared.message.MessageDecoder;
import cs340.shared.message.MessageEncoder;

@ClientEndpoint(
		decoders = MessageDecoder.class,
		encoders = MessageEncoder.class
)
public class ClientCommunicator {
	private static ClientCommunicator singleton = new ClientCommunicator("localhost:8080/command");
	private Session userSession = null;
	private MessageHandler messageHandler;

	public static ClientCommunicator getInstance() {
		if (singleton == null) {
			singleton = new ClientCommunicator("localhost:8080/command");
		}
		return singleton;
	}

	public ClientCommunicator(String address) {
		try {
			URI endpointURI = new URI(address);
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);

			this.messageHandler = new MessageHandler();
		} catch (Exception e) {
			System.out.println("Unable to connect to server...");
		}
	}

	/**
	 * Callback hook for Connection open events.
	 *
	 * @param userSession the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("Opening Websocket");
		this.userSession = userSession;
	}

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession the userSession which is getting closed.
	 * @param reason the reason for connection close
	 */
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("Closing Websocket: " + reason.getReasonPhrase());
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a client send a message.
	 * @param message The text message
	 */
	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	/**
	 * Sends a message containing a command object and an AuthToken to the server's websocket handler.
	 * @param message A ServerCommand wrapped in a message
	 */
	public void sendMessage(Message message) {
		this.userSession.getAsyncRemote().sendObject(message);
	}

	/**
	 * Sends a message containing only a string to the server's websocket handler. For testing.
	 *
	 * @param message
	 */
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}

	/**
	 * Message handler.
	 */
	private class MessageHandler {
		public void handleMessage(String message) {
			System.out.println(message); // change to execute commands
		}
	}

	/**
	 * For testing only.
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Opening connection with server...");
		ClientCommunicator.getInstance().sendMessage(new Message("test", new ServerCommand("test", "test")));
	}
}