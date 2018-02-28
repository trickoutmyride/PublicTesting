package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs340.ui.R;
import cs340.ui.presenters.DeckPresenter;
import cs340.ui.presenters.IDeckPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeckFragment extends Fragment implements IDeckFragment {

    private IDeckPresenter deckPresenter;

    public DeckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        deckPresenter = new DeckPresenter(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck, container, false);
    }

}
