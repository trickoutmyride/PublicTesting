package cs340.client.services;

import java.util.ArrayList;

import cs340.client.communication.ServerProxy;
import cs340.shared.interfaces.IServer;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.JoinGameRequest;

public class PregameService {
    public static void onGameListUpdated(ArrayList<Game> games) {
        ClientModel.getInstance().setGameList(games);
    }
}
