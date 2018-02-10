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
	//private static final String address = "wss://real.okcoin.cn:10440/websocket/okcoinapi";
	private static final String address = "wss://localhost:8080/command";
	private static ClientCommunicator singleton;
	private Session userSession = null;
	private MessageHandler messageHandler;
	private MessageEncoder encoder;
	private MessageDecoder decoder;


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
		} catch (Exception e) {
			System.out.println("error during Connection: " + e.getLocalizedMessage());
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
			this.messageHandler.handleMessage(message);
		}
	}

	/**
	 * register message handler
	 *
	 * @param msgHandler
	 */
	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}
	/**
	 * Sends a message containing a command object and an AuthToken to the server's websocket handler.
	 * @param message A ServerCommand wrapped in a message
	 */
	public void sendMessage(Message message) {
		String send = encoder.encode(message);
		this.userSession.getAsyncRemote().sendObject(send);
	}

	/**
	 * Sends a message containing ONLY A STRING to the server's websocket handler. For testing.
	 *
	 * @param message
	 */
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}

	/**
	 * Message handler.
	 */
	private static interface MessageHandler {
		public void handleMessage(String message);
	}

	/**
	 * For testing only.
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			// open websocket
			final ClientCommunicator clientEndPoint = ClientCommunicator.getInstance();

			// add listener
			clientEndPoint.addMessageHandler(new ClientCommunicator.MessageHandler() {
				public void handleMessage(String message) {
					System.out.println(message);
				}
			});
			System.out.println("Sending test message...");
			// send message to websocket
			clientEndPoint.sendMessage(new Message("test", new ServerCommand("test", "test")));
			//clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");  // For testing, does work (fails authentication though)

			// wait 5 seconds for messages from websocket
			Thread.sleep(5000);

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