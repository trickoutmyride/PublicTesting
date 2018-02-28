package cs340.ui.presenters;

import cs340.ui.fragments.IMapFragment;

public class MapPresenter implements IMapPresenter {

    private IMapFragment mapFragment;

    public MapPresenter(IMapFragment mapFragment){
        this.mapFragment = mapFragment;
    }
}
