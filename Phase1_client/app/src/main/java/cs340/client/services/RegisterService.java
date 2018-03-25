package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.requests.SignInRequest;
import cs340.client.communication.ServerProxy;

public class RegisterService {
	private static ServerProxy proxy = new ServerProxy();

	public static void register(String username, String password, String address) {
		proxy.register(new SignInRequest(username, password), address);
	}

	public static void onRegister(Player player) {
		ClientModel.getInstance().setCurrentPlayer(player);
	}
}
