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
    public void startGame(Game game) {
        ArrayList<Player> newPlayerList = new ArrayList<>();
        Player fakePlayer, fakePlayer2, fakePlayer3, fakePlayer4;
        fakePlayer = new Player("fakePlayer1", "p", "a");
        fakePlayer.setUsername("fakePlayer1");
        fakePlayer2 = new Player("fakePlayer2", "p", "a");
        fakePlayer2.setUsername("fakePlayer2");
        fakePlayer3 = new Player("fakePlayer3", "p", "a");
        fakePlayer3.setUsername("fakePlayer3");
        fakePlayer4 = new Player("fakePlayer4", "p", "a");
        fakePlayer4.setUsername("fakePlayer4");
        newPlayerList.add(fakePlayer);
        newPlayerList.add(fakePlayer2);
        newPlayerList.add(fakePlayer3);
        newPlayerList.add(fakePlayer4);

        _lobbyActivity.onPlayerListChanged(newPlayerList);
    }
}
