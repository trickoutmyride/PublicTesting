package cs340.client.communication;

import com.google.gson.Gson;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DecodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import cs340.client.command.CommandManager;
import cs340.client.command.CommandProcessor;
import cs340.shared.message.Message;
import cs340.shared.message.MessageDecoder;
import cs340.shared.message.MessageEncoder;
import cs340.shared.message.ServerMessage;
import cs340.shared.requests.SignInRequest;

@ClientEndpoint(
		decoders = MessageDecoder.class,
		encoders = MessageEncoder.class
)
public class ClientCommunicator {
	//private static final String address = "wss://real.okcoin.cn:10440/websocket/okcoinapi";
	//private static final String address = "ws://localhost:8080/ws/command";
	private static final String address = "ws://10.37.84.13:8080/ws/command";
	private static ClientCommunicator singleton;
	private Session userSession = null;
	private MessageHandler messageHandler;
	private MessageEncoder encoder;
	private MessageDecoder decoder;
	private Gson gson = new Gson();


	public static ClientCommunicator getInstance() {
		if (singleton == null) {
			System.out.println("Connecting to " + address);
			try {
				singleton = new ClientCommunicator(new URI(address));
			} catch (Exception e) {
				System.out.println("error at getInstance(): " + e.getLocalizedMessage());
			}
		}
		return singleton;
	}

	public ClientCommunicator(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);

			this.messageHandler = new MessageHandler();

		} catch (Exception e) {
			System.out.println("Error during Connection: " + e.getLocalizedMessage());
		}
		encoder = new MessageEncoder();
		decoder = new MessageDecoder();
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
			System.out.println("Receiving Message: " + message);
			try {
				this.messageHandler.handleMessage(message);
			} catch (Exception e) {
				System.out.println("Error handling message: " + e.getLocalizedMessage());
			}
		}
	}
	/*
	@OnMessage
	public void onMessage(Message message) {
		if (this.messageHandler != null) {
			System.out.println("Receiving Message Object: " + message);
			try {
				//this.messageHandler.handleMessage(message);
			} catch (Exception e) {
				System.out.println("Error handling message");
			}
		}
	}
	*/
	/**
	 * register message handler
	 *
	 * @param message
	 */
	public void sendMessage(ServerMessage message) {
		//String send = encoder.encode(message);
		System.out.println("Sending test message object..." + message.getId());
		//System.out.println(encoder.encode(message));
		//Log.d("ClientCommunicator", "Sending test message object..." + message.getId() + "\n" + message.getContents());
		//this.userSession.getAsyncRemote().sendObject(message);
		this.userSession.getAsyncRemote().sendText(gson.toJson(message));
	}

	/**
	 * Sends a message containing ONLY A STRING to the server's websocket handler. For testing.
	 *
	 * @param message
	 */

	public void sendMessage(String message) {
		System.out.println("Sending test message string...");
		this.userSession.getAsyncRemote().sendObject(message);
	}

	/**
	 * Message handler.
	 */
	private class MessageHandler {
		public void handleMessage(String msg) throws DecodeException {
			System.out.println(msg);
			Message message = decoder.decode(msg);
			CommandProcessor.handle(message);
		}
	}


	/**
	 * For testing only.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gson = new Gson();
		try {
			// open websocket
			final ClientCommunicator clientEndPoint = ClientCommunicator.getInstance();

			// add listener
			System.out.println("Sending test message...");
			// send message to websocket
			ServerMessage message = new ServerMessage("user", CommandManager.getInstance().makeCommand("register", new SignInRequest("user", "pass")));
			clientEndPoint.sendMessage(message);
			//clientEndPoint.sendMessage(new Message("user1", new ServerCommand("register", gson.toJson(new SignInRequest("user2", "pass2")))));
			//clientEndPoint.sendMessage("String Stuff");
			//clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");  // For testing, does work (fails authentication though)

			// wait 5 seconds for messages from websocket
			Thread.sleep(10000);

		} catch (Exception e) {
			System.err.println("Error in main(): " + e.getLocalizedMessage());
		}
	}
	/*
	public static void main(String args[]) {
		System.out.println("Opening connection with server...");
		ClientCommunicator.getInstance().sendMessage(new Message("test", new ServerCommand("test", "test")));
	}
	*/
}