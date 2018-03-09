package cs340.ui.presenters;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

import cs340.client.services.MapService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.GameMap;
import cs340.shared.model.MapRoute;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.fragments.interfaces.IMapFragment;
import cs340.ui.presenters.interfaces.IMapPresenter;

public class MapPresenter implements GameMap.Observer, IMapPresenter {
    private static Map<Integer, String> colors = new HashMap<>();
    private GameMap map = ClientModel.getInstance().getCurrentGame().getGameMap();
    private IMapFragment mapFragment;

    static {
        colors.put(R.color.train_black, "black");
        colors.put(R.color.train_blue, "blue");
        colors.put(R.color.train_grey, "grey");
        colors.put(R.color.train_green, "green");
        colors.put(R.color.train_orange, "orange");
        colors.put(R.color.train_pink, "pink");
        colors.put(R.color.train_red, "red");
        colors.put(R.color.train_white, "white");
        colors.put(R.color.train_yellow, "yellow");
    }

    public MapPresenter(IMapFragment mapFragment){
        this.mapFragment = mapFragment;
        map.addObserver(this);
    }

    public void claimRoute(MapRoute route) {
        String color = colors.get(route.getColor());
        String end = route.getStop().getKey();
        Player player = ClientModel.getInstance().getCurrentPlayer();
        String start = route.getStart().getKey();
        MapService.claimRoute(player, start, end, color);
    }

    public void detach() {
        map.removeObserver(this);
    }

    @Override
    public void onRouteClaimed(Map<Pair<String, String>, MapRoute> routes) {
        mapFragment.onRouteClaimed(routes);
    }
}
