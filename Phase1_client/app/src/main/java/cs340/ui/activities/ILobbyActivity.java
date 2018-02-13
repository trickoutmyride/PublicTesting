package cs340.ui.activities;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.shared.model.Game;

public interface ILobbyActivity {
    void onError(String message);
    void onGameStarted();
    void onGameUpdated(Game game);
}
