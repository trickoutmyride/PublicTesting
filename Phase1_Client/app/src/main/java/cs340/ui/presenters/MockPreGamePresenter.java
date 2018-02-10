package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.PreGameActivity;

/**
 * Created by sam on 2/10/18.
 */

public class MockPreGamePresenter implements IPreGamePresenter {

    private PreGameActivity preGameActivity;

    public MockPreGamePresenter(PreGameActivity preGameActivity) {
        this.preGameActivity = preGameActivity;
    }

    @Override
    public void detach() {
        //stub
    }

    @Override
    public void createGame(String name, int capacity, Player currentPlayer) {
        Game game = new Game();
        game.setGameName(name);
        game.setCapacity(capacity);
        ArrayList<Player> p = new ArrayList<>();
        p.add(currentPlayer);
        game.setPlayers(p);
        preGameActivity.onGameJoined(game);
    }

    @Override
    public void joinGame(Game game) {
        preGameActivity.onGameJoined(game);
    }
}
