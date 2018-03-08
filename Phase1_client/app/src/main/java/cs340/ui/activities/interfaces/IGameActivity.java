package cs340.ui.activities.interfaces;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;

public interface IGameActivity {
    //stub
    void onHistoryItemUpdated(String historyItem);
    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);
    void onChatUpdated(String message);
    void onPlayerCardsUpdated(ArrayList<TrainCard> newCards);
    Player getCurrentPlayer();
}
