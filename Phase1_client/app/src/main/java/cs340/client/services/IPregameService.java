package cs340.client.services;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;

public interface IPregameService extends IService {
    void createGame(String name, Player player, int capacity, String color);
    void joinGame(int gameID, Player player, String color);
    void onGameJoined(Game game);
    void onGameListUpdated(ArrayList<Game> games);
}
