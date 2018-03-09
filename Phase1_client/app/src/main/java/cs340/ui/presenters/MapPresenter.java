package cs340.ui.presenters;

import android.util.Pair;

import java.util.Map;

import cs340.shared.model.ClientModel;
import cs340.shared.model.GameMap;
import cs340.shared.model.MapRoute;
import cs340.ui.fragments.interfaces.IMapFragment;
import cs340.ui.presenters.interfaces.IMapPresenter;

public class MapPresenter implements GameMap.Observer, IMapPresenter {
    private GameMap map = ClientModel.getInstance().getCurrentGame().getGameMap();
    private IMapFragment mapFragment;

    public MapPresenter(IMapFragment mapFragment){
        this.mapFragment = mapFragment;
        map.addObserver(this);
    }

    public void detach() {
        map.removeObserver(this);
    }

    @Override
    public void onRouteClaimed(Map<Pair<String, String>, MapRoute> routes) {
        mapFragment.onRouteClaimed(routes);
    }
}
