package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.GameList;
import cs340.shared.model.Player;
import cs340.ui.activities.PreGameActivity;

/**
 * Created by sam on 2/10/18.
 */

public class MockPreGamePresenter implements IPregamePresenter {

    private PreGameActivity preGameActivity;

    public MockPreGamePresenter(PreGameActivity preGameActivity) {
        this.preGameActivity = preGameActivity;
    }

    @Override
    public void detach() {
        //stub
    }

    @Override
    public void createGame(String name, Player player, int capacity, String color) {

    }

    @Override
    public void joinGame(int gameID, Player player, String color) {
        preGameActivity.onError("nah fam.");
        preGameActivity.onGameListUpdated(null);
    }

    @Override
    public void onGameListUpdated(GameList games) {

    }


    @Override
    public void onError(String message) {

    }

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {

    }
}
