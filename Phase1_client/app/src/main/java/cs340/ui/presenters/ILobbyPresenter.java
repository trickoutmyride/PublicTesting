package cs340.ui.presenters;

import cs340.shared.model.Game;

public interface ILobbyPresenter extends Game.LobbyObserver {
    void detach();
    void startGame();
}
