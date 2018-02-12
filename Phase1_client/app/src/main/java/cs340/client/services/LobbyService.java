package cs340.client.services;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.interfaces.IServer;
import cs340.shared.requests.StartGameRequest;
import cs340.shared.model.Game;

public class LobbyService implements ILobbyService {
    private IServer proxy = null;
    private static LobbyService instance = new LobbyService();

    private LobbyService() {}

    public static LobbyService getInstance() {
        return instance;
    }

    @Override
    public void onGameStarted() {
        ClientModel.getInstance().getCurrentGame().start();
    }

    @Override
    public void setProxy(IServer proxy) {
        this.proxy = proxy;
    }

    @Override
    public void startGame(Player player) {
        if (proxy != null) proxy.startGame(new StartGameRequest(player));
    }
}