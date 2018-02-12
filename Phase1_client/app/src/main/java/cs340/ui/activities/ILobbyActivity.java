package cs340.ui.activities;

import java.util.ArrayList;

import cs340.shared.model.Player;

public interface ILobbyActivity {
    void onError(String message);
    void onGameStarted();
    void onRosterUpdated(ArrayList<Player> players);
}
