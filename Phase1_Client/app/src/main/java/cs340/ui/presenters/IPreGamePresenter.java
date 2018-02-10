package cs340.ui.presenters;


import cs340.shared.model.Game;

public interface IPreGamePresenter {
    void detach();
    void createGame(Game game);
    void joinGame(Game game);
}