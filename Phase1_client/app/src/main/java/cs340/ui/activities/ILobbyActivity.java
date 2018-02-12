package cs340.ui.activities;

import java.util.ArrayList;

import cs340.shared.model.Player;

public interface ILobbyActivity {
    public void onPlayerListChanged(ArrayList<Player> newPlayerList);
}
