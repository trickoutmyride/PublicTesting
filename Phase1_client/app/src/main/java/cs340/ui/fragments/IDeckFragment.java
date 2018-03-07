package cs340.ui.fragments;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;

public interface IDeckFragment {
    Game getCurrentGame();
    Player getCurrentPlayer();
    void onFaceUpCardsUpdated(ArrayList<TrainCard> cards);
}
