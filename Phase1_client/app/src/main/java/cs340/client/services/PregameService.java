package cs340.client.services;

import java.util.ArrayList;

import cs340.shared.interfaces.IServer;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.requests.CreateGameRequest;
import cs340.shared.requests.JoinGameRequest;

public class PregameService implements IPregameService {
    private static PregameService instance = new PregameService();
    private IServer proxy = null;

    private PregameService() {}

    @Override
    public void createGame(String name, Player player, int capacity, String color) {
        if (proxy != null) proxy.createGame(new CreateGameRequest(name, player, capacity, color));
    }

    public static PregameService getInstance() {
        return instance;
    }

    @Override
    public void joinGame(int gameID, Player player, String color) {
        if (proxy != null) proxy.joinGame(new JoinGameRequest(gameID, player, color));
    }

    @Override
    public void onGameJoined(Game game) {
        ClientModel.getInstance().setCurrentGame(game);
    }

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {
        ClientModel.getInstance().setGameList(games);
    }

    @Override
    public void setProxy(IServer proxy) {
        this.proxy = proxy;
    }
}
