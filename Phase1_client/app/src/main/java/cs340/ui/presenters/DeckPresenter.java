package cs340.ui.presenters;

import cs340.ui.fragments.IDeckFragment;

public class DeckPresenter implements IDeckPresenter {

    private IDeckFragment deckFragment;

    public DeckPresenter(IDeckFragment deckFragment){
        this.deckFragment = deckFragment;
    }
}
