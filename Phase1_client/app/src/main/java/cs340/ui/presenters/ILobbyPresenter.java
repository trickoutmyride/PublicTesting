package cs340.ui.presenters;

import cs340.shared.model.ClientModel;

public interface ILobbyPresenter extends ClientModel.GameLobbyObserver {
    void detach();
    void startGame();
}
