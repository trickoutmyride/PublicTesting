package cs340.ui.presenters;

import cs340.ui.fragments.IPlayersFragment;

public class PlayersPresenter implements IPlayersPresenter {

    private IPlayersFragment otherPlayersFragment;

    public PlayersPresenter(IPlayersFragment otherPlayersFragment){
        this.otherPlayersFragment = otherPlayersFragment;
    }
}
