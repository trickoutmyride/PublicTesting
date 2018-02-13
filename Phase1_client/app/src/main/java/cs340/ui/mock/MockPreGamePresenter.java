package cs340.ui.mock;

import java.util.ArrayList;
import java.util.Random;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.PreGameActivity;
import cs340.ui.presenters.IPregamePresenter;

/**
 * Created by sam on 2/13/18.
 */

public class MockPreGamePresenter implements IPregamePresenter {

    private PreGameActivity _preGameActivity;

    public MockPreGamePresenter(PreGameActivity preGameActivity){
        _preGameActivity = preGameActivity;
        //_preGameActivity.onGameListUpdated(fakeGamesList);
    }

    @Override
    public void detach() {

    }

    @Override
    public void createGame(String name, Player player, int capacity, String color) {

    }

    @Override
    public void joinGame(int gameID, Player player, String color) {

    }

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {

    }

    @Override
    public void onError(String message) {

    }
}
