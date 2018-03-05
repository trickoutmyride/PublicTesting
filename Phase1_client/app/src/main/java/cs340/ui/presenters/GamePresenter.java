package cs340.ui.presenters;

import cs340.ui.activities.IGameActivity;

public class GamePresenter implements IGamePresenter {
    private IGameActivity gameActivity;
    public GamePresenter(IGameActivity gameActivity){
        this.gameActivity = gameActivity;
    }
}
