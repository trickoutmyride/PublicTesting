package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.client.services.ChatService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.ui.activities.interfaces.IGameActivity;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.presenters.interfaces.IGamePresenter;

//GamePresenter implements HistoryObserver to update the history in the Activity when the dialog is not currently shown

public class GamePresenter implements IGamePresenter, ClientModel.HistoryObserver, ClientModel.ChatObserver {
    private IGameActivity gameActivity;

    public GamePresenter(IGameActivity gameActivity){
        this.gameActivity = gameActivity;
        ClientModel.getInstance().addHistoryObserver(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onHistoryUpdated(String historyItem) {
        gameActivity.updateHistory(historyItem);
    }

    @Override
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards){
        gameActivity.onDrawnDestinationCards(cards);
    }

    @Override
    public void onMessageUpdated(String message) {
        gameActivity.onChatUpdated(message);
    }

    @Override
    public void sendMessage(String message) {
        ChatService.chat(gameActivity.getCurrentPlayer(), message);
    }

}
