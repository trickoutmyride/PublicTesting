package cs340.ui.presenters;

import android.app.Activity;

import java.util.ArrayList;

import cs340.client.services.ChatService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.ui.activities.GameActivity;
import cs340.ui.activities.interfaces.IGameActivity;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.presenters.interfaces.IGamePresenter;

//GamePresenter implements HistoryObserver to update the history in the Activity when the dialog is not currently shown

public class GamePresenter implements IGamePresenter, ClientModel.HistoryObserver, ClientModel.ChatObserver {
    private IGameActivity gameActivity;

    public GamePresenter(IGameActivity gameActivity){
        this.gameActivity = gameActivity;
        ClientModel.getInstance().addChatObserver(this);
        ClientModel.getInstance().addHistoryObserver(this);
        ClientModel.getInstance().addGameObserver(this);
    }

    public void detach(){
        ClientModel.getInstance().removeChatObserver(this);
        ClientModel.getInstance().removeHistoryObserver(this);
        ClientModel.getInstance().addGameObserver(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onHistoryUpdated(String historyItem) {
        gameActivity.onHistoryItemUpdated(historyItem);
    }

    @Override
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards){
        gameActivity.onDrawnDestinationCards(cards);
    }

    @Override
    public void onMessageUpdated(final String message) {
        gameActivity.onChatUpdated(message);
    }

    @Override
    public void sendMessage(String message) {
        ChatService.chat(gameActivity.getCurrentPlayer(), message);
    }

    @Override
    public void onDestinationCardsUpdated(Player player){
        gameActivity.onDestinationCardsUpdated(player);
    }

}
