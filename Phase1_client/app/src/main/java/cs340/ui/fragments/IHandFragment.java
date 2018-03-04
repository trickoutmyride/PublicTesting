package cs340.ui.fragments;

import java.util.ArrayList;

import cs340.shared.model.TrainCard;

public interface IHandFragment {
    void onHandUpdated(ArrayList<TrainCard> cards);
}
