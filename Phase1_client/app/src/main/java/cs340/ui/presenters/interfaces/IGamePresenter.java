package cs340.ui.presenters.interfaces;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;

public interface IGamePresenter extends ClientModel.GameObserver {

    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);

}
