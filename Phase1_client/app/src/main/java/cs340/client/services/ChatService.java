package cs340.client.services;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.requests.ChatRequest;

/**
 * Created by Mark on 3/7/2018.
 */

public class ChatService {
	private static ServerProxy proxy = new ServerProxy();

	public static void chat(Player player, String message) {
		proxy.chat(new ChatRequest(player, message));
	}

	public static void onChat(String message) {
		ClientModel.getInstance().newMessage(message);
	}
}
