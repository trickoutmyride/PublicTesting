package cs340.ui.fragments.interfaces;

import java.util.ArrayList;

import cs340.shared.model.Player;

public interface IPlayersFragment {
    public void onPlayerUpdated(Player player);
    public void initiatePlayers(ArrayList<Player> players);
}
