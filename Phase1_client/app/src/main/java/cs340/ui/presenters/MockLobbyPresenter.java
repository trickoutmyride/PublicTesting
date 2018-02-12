package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.LobbyActivity;

/**
 * Created by sam on 2/11/18.
 */

public class MockLobbyPresenter implements ILobbyPresenter {

    private LobbyActivity _lobbyActivity;

    public MockLobbyPresenter(LobbyActivity lobbyActivity) {
        _lobbyActivity = lobbyActivity;
    }

    @Override
    public void detach() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onGameStarted() {

    }

    @Override
    public void onRosterUpdated(ArrayList<Player> players) {

    }
}
