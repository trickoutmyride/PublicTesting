package cs340.ui.activities.interfaces;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;

public interface IGameActivity {
    //stub
    void onHistoryItemUpdated(String historyItem);
    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);
    void onChatUpdated(String message);
    void onPlayerCardsUpdated(final int index, final TrainCard oldCard, final TrainCard newCard, final Player player);
    void onPlayerCardsUpdated(Player player);
    Player getCurrentPlayer();
    void onDestinationCardsUpdated(Player player);
    void onPlayerUpdated(final Player player);
    boolean myTurn();
    int getTurnIndex();
    void onTurnUpdated(final Game game);
}
