package client.services;

import java.util.ArrayList;

import client.models.GameModel;

public interface IPregameService extends IService {
    void createGame(String name);
    void joinGame(long name);
    void onGameListUpdated(ArrayList<GameModel> games);
}
