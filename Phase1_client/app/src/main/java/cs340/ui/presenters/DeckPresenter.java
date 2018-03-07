package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.client.services.DeckService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.TrainCard;
import cs340.ui.fragments.IDeckFragment;

public class DeckPresenter implements IDeckPresenter, ClientModel.DeckObserver {

    private IDeckFragment deckFragment;

    public DeckPresenter(IDeckFragment deckFragment){
        this.deckFragment = deckFragment;
        ClientModel.getInstance().addDeckObserver(this);
    }

    @Override
    public void detach(){
        ClientModel.getInstance().removeDeckObserver(this);
    }

    //TODO: DeckPresenter onError
    @Override
    public void onError(String message) {}


    @Override
    public void updateFaceUpDeck(ArrayList<TrainCard> cards) {
        deckFragment.onFaceUpCardsUpdated(cards);
    }

    @Override
    public void cardSelected(TrainCard card){
        DeckService.drawTrainCard(deckFragment.getCurrentGame().getGameID(), card, deckFragment.getCurrentPlayer());
    }

}
