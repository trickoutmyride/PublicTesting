package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;

public interface IGamePresenter {

    void onDrawnDestinationCards(ArrayList<DestinationCard> cards);

}
