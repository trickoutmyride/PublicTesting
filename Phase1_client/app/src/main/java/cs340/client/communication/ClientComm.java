package cs340.client.communication;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import cs340.shared.message.Message;
import cs340.shared.message.MessageDecoder;
import cs340.shared.message.MessageEncoder;
import cs340.client.command.CommandProcessor;

@ClientEndpoint(
		decoders = MessageDecoder.class,
		encoders = MessageEncoder.class)

public class ClientComm {
	@OnOpen
	public void onOpen(Session p) {
		try {
			p.getBasicRemote().sendText("Hello World!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(Message message) {
		System.out.println("Received Message: " + message.getId());
		CommandProcessor.handle(message);
	}
}