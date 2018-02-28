package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs340.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeckFragment extends Fragment {

    public DeckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck, container, false);
    }

}
