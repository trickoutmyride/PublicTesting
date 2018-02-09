package cs340.server.communication;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cs340.shared.message.Message;
import cs340.shared.message.MessageDecoder;
import cs340.shared.message.MessageEncoder;

@ServerEndpoint(value = "/command",
		decoders = MessageDecoder.class,
encoders = MessageEncoder.class) //Server endpoint
public class WebSocketHandler {
	
	private Session session; //Websocket server session
	private static HashMap<String,Session> userSessions = new HashMap<>();
	private static final String username = "Server";
	
	//Get session/WebSocket connection
	@OnOpen
	public void onOpen(Session session) throws IOException{
		//session.getBasicRemote().sendText("onOpen");
		System.out.println("here");
	}
	
	//Handles new messages
	//Decodes the JSON and sends them directly to CommandHandler
	@OnMessage
	public void onMessage(Session session, Message message) throws IOException{
		userSessions.put(message.getId(), session); //Keeps the map of sessions up to date
		CommandProcessor.handle(message);
	}
	
	//Sends a message to a specific user
	//@param id  the session id (auth token)
	public static void sendMessage(String id, Message message){
		Session userSession = userSessions.get(id);
		try {
			userSession.getBasicRemote().sendObject(message); //Sends the message
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		} 
	}
	
	//When the connection closes
	@OnClose
	public void onClose(Session session) throws IOException{}
	
	//When an error occurs
	@OnError
	public void onError(Session session, Throwable throwable){}
	
}
