package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.GameList;
import cs340.ui.activities.IPreGameActivity;

public class PregamePresenter implements IPregamePresenter {
    private IPreGameActivity activity;

    public PregamePresenter(IPreGameActivity activity) {
        this.activity = activity;
        ClientModel.getInstance().addGameListObserver(this);
    }

    @Override
    public void createGame(String name) {

    }

    @Override
    public void detach() {
        ClientModel.getInstance().removeGameListObserver(this);
    }

    @Override
    public void joinGame(long id) {

    }

    @Override
    public void onError(String message) {
        activity.onError(message);
    }

    @Override
    public void onGameListUpdated(GameList games) {
        activity.onGameListUpdated(games);
    }
}
