package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs340.ui.R;
import cs340.ui.presenters.HandPresenter;
import cs340.ui.presenters.IHandPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandFragment extends Fragment implements IHandFragment {

    private IHandPresenter handPresenter;


    public HandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handPresenter = new HandPresenter(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hand, container, false);
    }

}
