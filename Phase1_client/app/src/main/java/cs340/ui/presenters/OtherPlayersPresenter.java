package cs340.ui.presenters;

import cs340.ui.fragments.IOtherPlayersFragment;

public class OtherPlayersPresenter implements IOtherPlayersPresenter {

    private IOtherPlayersFragment otherPlayersFragment;

    public OtherPlayersPresenter(IOtherPlayersFragment otherPlayersFragment){
        this.otherPlayersFragment = otherPlayersFragment;
    }
}
