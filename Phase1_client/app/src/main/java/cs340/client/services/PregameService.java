package client.services;

import java.util.ArrayList;

import client.commands.PregameCommand;
import client.models.ClientModel;
import client.models.GameModel;

public class PregameService implements IPregameService {
    private static PregameService instance = new PregameService();
    private IService.Proxy proxy = null;

    private PregameService() {}

    @Override
    public void createGame(String name) {
        if (proxy != null) proxy.sendCommand(new PregameCommand());
    }

    public static PregameService getInstance() {
        return instance;
    }

    @Override
    public void joinGame(long name) {
        if (proxy != null) proxy.sendCommand(new PregameCommand());
    }

    @Override
    public void onGameListUpdated(ArrayList<GameModel> games) {
        ClientModel.getInstance().setGameList(games);
    }

    @Override
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
