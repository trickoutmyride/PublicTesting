package cs340.ui.presenters;

import cs340.ui.fragments.IHandFragment;

public class HandPresenter implements IHandPresenter {

    private IHandFragment handFragment;

    public HandPresenter(IHandFragment handFragment){
        this.handFragment = handFragment;
    }
}
