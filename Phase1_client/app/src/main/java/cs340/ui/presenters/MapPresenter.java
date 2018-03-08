package cs340.ui.presenters;

import cs340.ui.fragments.interfaces.IMapFragment;
import cs340.ui.presenters.interfaces.IMapPresenter;

public class MapPresenter implements IMapPresenter {

    private IMapFragment mapFragment;

    public MapPresenter(IMapFragment mapFragment){
        this.mapFragment = mapFragment;
    }
}
