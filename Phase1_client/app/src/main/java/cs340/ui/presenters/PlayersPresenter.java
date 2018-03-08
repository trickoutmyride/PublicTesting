package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.ui.fragments.interfaces.IPlayersFragment;
import cs340.ui.presenters.interfaces.IPlayersPresenter;

//PlayersPresenter only updates the player info, does not make calls to services
public class PlayersPresenter implements IPlayersPresenter, ClientModel.PlayersObserver {

    private IPlayersFragment playersFragment;

    public PlayersPresenter(IPlayersFragment playersFragment){
        this.playersFragment = playersFragment;
        ClientModel.getInstance().addPlayersObserver(this);
    }

    public void detach(){
        ClientModel.getInstance().removePlayersObserver(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onPlayerUpdated(Player player) {
        playersFragment.onPlayerUpdated(player);
    }

    @Override
    public void onPlayersUpdated(ArrayList<Player> players) {
        for (Player player : players){
            playersFragment.onPlayerUpdated(player);
        }
    }
}
