package cs340.ui.presenters;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;

public interface ILobbyPresenter extends ClientModel.GameLobbyObserver {
    void detach();
    void startGame();
}
