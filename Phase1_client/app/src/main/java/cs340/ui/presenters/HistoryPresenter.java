package cs340.ui.presenters;

import cs340.shared.model.ClientModel;
import cs340.ui.fragments.interfaces.IHistoryFragment;
import cs340.ui.presenters.interfaces.IHistoryPresenter;

/**
 * Created by sam on 3/7/18.
 */

/*
//HistoryPresenter just updates the history, no need to call any Services
public class HistoryPresenter implements IHistoryPresenter {

    private IHistoryFragment historyFragment;

    public HistoryPresenter(IHistoryFragment historyFragment){
        this.historyFragment = historyFragment;
        ClientModel.getInstance().addHistoryObserver(this);
    }

    public void detach(){
        ClientModel.getInstance().removeHistoryObserver(this);
    }
}

*/