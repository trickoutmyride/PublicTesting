package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.requests.SignInRequest;
import cs340.client.communication.ServerProxy;

public class RegisterService {
	private static ServerProxy proxy = new ServerProxy();

	public static void register(String username, String password) {
		proxy.register(new SignInRequest(username, password));
	}

	public static void onRegister(Player player) {
		ClientModel.getInstance().setCurrentPlayer(player);
	}
}
