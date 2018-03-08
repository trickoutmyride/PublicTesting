package cs340.ui.activities.interfaces;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.shared.model.Game;

public interface ILobbyActivity {
    void onError(String message);
    void onGameStarted(Game game);
    void onGameUpdated(Game game);
}
