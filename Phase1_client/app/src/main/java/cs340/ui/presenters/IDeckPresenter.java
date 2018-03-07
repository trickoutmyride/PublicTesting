package cs340.ui.presenters;

import cs340.shared.model.TrainCard;

public interface IDeckPresenter {
    void cardSelected(TrainCard card);
    void detach();
}
