package cs340.ui.presenters.interfaces;

import cs340.shared.model.TrainCard;

public interface IDeckPresenter {
    void cardSelected(int index);
    void drawFromDeck();
    void detach();
}
