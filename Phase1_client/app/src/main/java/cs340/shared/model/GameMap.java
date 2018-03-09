package cs340.shared.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs340.ui.R;

public class GameMap {
    private Game game;
    private List<Observer> observers = new ArrayList<Observer>();
    private Map<Pair<String, String>, MapRoute> routes = MapRoute.copyRouteMap();

    public GameMap(Game game) {
        this.game = game;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void onRouteClaimed(Player player, String start, String end) {
        String colorName = game.getColors().get(player.getUsername());
        Integer color;
        switch (colorName) {
            case "blue":
                color = R.color.player_blue;
                break;
            case "green":
                color = R.color.player_green;
                break;
            case "purple":
                color = R.color.player_purple;
                break;
            case "red":
                color = R.color.player_red;
                break;
            case "yellow":
                color = R.color.player_yellow;
                break;
            default:
                color = 0;
        }
        routes.get(new Pair<>(start, end)).setColor(color);
        for (Observer observer : observers) observer.onRouteClaimed(routes);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public interface Observer {
        void onRouteClaimed(Map<Pair<String, String>, MapRoute> routes);
    }
}
