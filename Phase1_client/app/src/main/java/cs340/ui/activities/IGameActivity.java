package cs340.ui.activities;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;

public interface IGameActivity {
    //stub
    void updateHistory(String historyItem);
    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);
    void onChatUpdated(String message);
}
