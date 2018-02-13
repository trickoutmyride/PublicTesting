package cs340.ui.activities;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.GameList;

public interface IPreGameActivity {

    void onError(String message);

    void onGameListUpdated(ArrayList<Game> games);

    void onGameJoined(Game game);
}