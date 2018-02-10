package cs340.ui.presenters;


import cs340.shared.model.Game;
import cs340.shared.model.Player;

public interface IPreGamePresenter {
    void detach();
    void createGame(String name, int capacity, Player currentPlayer);
    void joinGame(Game game);
}