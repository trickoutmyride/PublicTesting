package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.ui.activities.IGameActivity;

//GamePresenter implements HistoryObserver to update the history in the Activity when the dialog is not currently shown

public class GamePresenter implements IGamePresenter, ClientModel.HistoryObserver {
    private IGameActivity gameActivity;

    public GamePresenter(IGameActivity gameActivity){
        this.gameActivity = gameActivity;
        ClientModel.getInstance().addHistoryObserver(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onHistoryUpdated(ArrayList<String> history) {
        gameActivity.updateHistory(history);
    }

    @Override
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards){
        gameActivity.onDrawnDestinationCards(cards);
    }

}
