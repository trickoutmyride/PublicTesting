package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs340.ui.R;
import cs340.ui.presenters.IOtherPlayersPresenter;
import cs340.ui.presenters.OtherPlayersPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherPlayersFragment extends Fragment implements IOtherPlayersFragment {

    private IOtherPlayersPresenter otherPlayersPresenter;

    public OtherPlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        otherPlayersPresenter = new OtherPlayersPresenter(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_players, container, false);
    }

}
