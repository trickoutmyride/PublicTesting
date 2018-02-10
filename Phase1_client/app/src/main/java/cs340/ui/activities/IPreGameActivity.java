package cs340.ui.activities;

import cs340.shared.model.Game;
import cs340.shared.model.GameList;

public interface IPreGameActivity {

    void onError(String message);

    void onGameListUpdated(GameList games);

    void onGameJoined(Game game);
}