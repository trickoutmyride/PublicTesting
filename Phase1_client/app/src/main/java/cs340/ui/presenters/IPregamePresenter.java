package cs340.ui.presenters;

import client.models.ClientModel;

public interface IPregamePresenter extends ClientModel.GameListObserver {

    void detach();
    void createGame(String name);
    void joinGame(long id);
}
