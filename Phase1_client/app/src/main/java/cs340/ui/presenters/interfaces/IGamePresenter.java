package cs340.ui.presenters.interfaces;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.fragments.HistoryFragment;

public interface IGamePresenter extends ClientModel.GameObserver, ChatFragment.ChatFragmentListener {

    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);

}
