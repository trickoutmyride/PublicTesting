package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs340.ui.R;
import cs340.ui.presenters.IMapPresenter;
import cs340.ui.presenters.MapPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements IMapFragment {

    private IMapPresenter mapPresenter;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mapPresenter = new MapPresenter(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

}
