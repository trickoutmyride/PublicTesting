package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.shared.model.ClientModel;
import cs340.ui.fragments.IHistoryFragment;

/**
 * Created by sam on 3/7/18.
 */

//HistoryPresenter just updates the history, no need to call any Services
public class HistoryPresenter implements IHistoryPresenter, ClientModel.HistoryObserver {

    private IHistoryFragment historyFragment;

    public HistoryPresenter(IHistoryFragment historyFragment){
        this.historyFragment = historyFragment;
        ClientModel.getInstance().addHistoryObserver(this);
    }

    public void detach(){
        ClientModel.getInstance().removeHistoryObserver(this);
    }

    //TODO: Implement HistoryPresenter onError
    @Override
    public void onError(String message) {

    }

    @Override
    public void onHistoryUpdated(ArrayList<String> history) {
        historyFragment.updateHistory(history);
    }
}
