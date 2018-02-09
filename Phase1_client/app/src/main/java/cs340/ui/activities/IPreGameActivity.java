package cs340.ui.activities;

import cs340.shared.model.GameList;

public interface IPreGameActivity {

    void onError(String message);

    void onGameListUpdated(GameList games);
}