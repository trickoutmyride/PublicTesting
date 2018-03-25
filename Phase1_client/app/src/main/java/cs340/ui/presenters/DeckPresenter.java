package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.client.services.DeckService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.fragments.interfaces.IDeckFragment;
import cs340.ui.presenters.interfaces.IDeckPresenter;

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

    @Override
    public void onError(String message) {
        deckFragment.getGameActivity().onError(message);
    }

    @Override
    public void updateFaceUpDeck(int index, TrainCard oldCard, TrainCard newCard, Player player, ArrayList<TrainCard> faceUpCards) {
        deckFragment.getGameActivity().onPlayerCardsUpdated(index, oldCard, newCard, player, faceUpCards);
    }

    @Override
    public void cardSelected(int index){
        DeckService.drawFaceup(deckFragment.getCurrentPlayer(), index);
    }

    @Override
    public void drawFromDeck(){
        DeckService.drawTrainCard(deckFragment.getCurrentPlayer());
    }
}
