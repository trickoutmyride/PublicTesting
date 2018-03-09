package cs340.client.services;

import cs340.client.communication.ServerProxy;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.EndTurnRequest;

/**
 * Created by Mark on 3/9/2018.
 */

public class TurnService {
	private static ServerProxy proxy = new ServerProxy();

	public static void endTurn(Player player) {
		proxy.endTurn(new EndTurnRequest(player));
	}

	public static void nextTurn(Game game) {
		ClientModel.getInstance().changeTurn(game);
	}
}
