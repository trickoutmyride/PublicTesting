package cs340.ui.presenters;

import cs340.shared.model.ClientModel;
import cs340.shared.model.GameList;
import cs340.shared.model.Player;

public interface IPregamePresenter extends ClientModel.GameListObserver {

    void detach();
    void createGame(String name, Player player, int capacity, String color);
    void joinGame(int gameID, Player player, String color);
    void onGameListUpdated(GameList games);
}
