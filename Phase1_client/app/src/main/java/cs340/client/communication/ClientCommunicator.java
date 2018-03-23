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

	private static final String address = "ws://10.24.217.139:8080/ws/command";
	private static ClientCommunicator singleton;
	private Session userSession = null;
	private MessageHandler messageHandler;
	private Gson gson = new Gson();

	/**
	 * @pre class variable address is a valid address for the Java server.
	 * @return The Singleton ClientCommunicator
	 * @post Creates a new ClientCommunicator to be the Singleton if there wasn't one already.
	 */
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

	/**
	 * @pre There is no other ClientCommunicator created.
	 * @pre endpointURI is a valid address for the Java server.
	 * @param endpointURI
	 * @post WebSocket is connected.
	 *
	 */
	private ClientCommunicator(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);

			this.messageHandler = new MessageHandler();

		} catch (Exception e) {
			System.out.println("Error during Connection: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Callback for Connection open events.
	 * @pre the WebSocket has opened
	 * @param userSession the userSession which is opened.
	 * @post the userSession variable in the ClientCommunicator Singleton is set as the param Session.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("Opening Websocket");
		this.userSession = userSession;
	}

	/**
	 * Callback hook for Connection close events.
	 * @pre Connection has closed.
	 * @param userSession the userSession which is getting closed.
	 * @param reason the reason for connection close
	 * @post the userSession variable in the ClientCommunicator Singleton is set to null.
	 */
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("Closing Websocket: " + reason.getReasonPhrase());
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a client receives a message.
	 * @pre A message has been received.
	 * @param message The message in JSON String format
	 * @post The message received is passed to the MessageHandler that ClientCommunicator has a pointer to.
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

	/**
	 * @pre WebSocket connection is currently open.
	 * @param message The ServerMessage desired to be sent to the server.
	 * @post The ServerMessage is sent to the server via the WebSocket connection.
	 */
	public void sendMessage(ServerMessage message) {
		System.out.println("Sending message object..." + message.getId());
		this.userSession.getAsyncRemote().sendText(gson.toJson(message));
	}

	/**
	 * Message handler.
	 */
	private class MessageHandler {
		public void handleMessage(String msg) throws DecodeException {
			System.out.println(msg);
			Message message = gson.fromJson(msg, Message.class);
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
}