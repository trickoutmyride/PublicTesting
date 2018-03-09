package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.fragments.interfaces.IHandFragment;
import cs340.ui.presenters.interfaces.IHandPresenter;

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

    @Override
    public void onError(String message) {

    }

    @Override
    public void onTrainCardsUpdated(Player player) {
        System.out.println("HandPresenter: onTrainCardsUpdated!");
        handFragment.onTrainCardsUpdated(player);
    }

    @Override
    public void onDestinationCardsUpdated(ArrayList<DestinationCard> cards) {

    }

}
