package cs340.ui.presenters.interfaces;

import cs340.shared.model.TrainCard;

public interface IDeckPresenter {
    void cardSelected(TrainCard card);
    void detach();
}
