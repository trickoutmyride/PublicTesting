package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.TrainCard;
import cs340.ui.fragments.IHandFragment;

//HandPresenter only needs to update the cards in the hand so it doesn't call any services
public class HandPresenter implements IHandPresenter, ClientModel.HandObserver {

    private IHandFragment handFragment;

    public HandPresenter(IHandFragment handFragment){
        this.handFragment = handFragment;
        ClientModel.getInstance().addHandObserver(this);
    }

    @Override
    public void detach() {
        ClientModel.getInstance().removeHandObserver(this);
    }

    //TODO: Presenter onError method?
    @Override
    public void onError(String message) {

    }

    @Override
    public void onTrainCardsUpdated(ArrayList<TrainCard> cards) {
        handFragment.onTrainCardsUpdated(cards);
    }

    //TODO: Add destination card hand presenter functionality
    @Override
    public void onDestinationCardsUpdated(ArrayList<DestinationCard> cards) {

    }

}
