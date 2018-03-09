package cs340.shared.model;

import android.graphics.Color;
import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs340.ui.R;

public class MapRoute {
    private static final Map<Pair<String, String>, MapRoute> routeMap = new HashMap<>();
    private Integer color;
    private Integer length;
    private City start;
    private City stop;

    static {
        List<MapRoute> routes = new ArrayList<>();
        routes.add(new MapRoute(City.ATLANTA, City.CHARLESTON, 2, R.color.train_grey));
        routes.add(new MapRoute(City.ATLANTA, City.MIAMI, 5, R.color.train_blue));
        routes.add(new MapRoute(City.ATLANTA, City.NASHVILLE, 1, R.color.train_grey));
        routes.add(new MapRoute(City.ATLANTA, City.NEW_ORLEANS, 4, R.color.train_orange)); // ORANGE
        routes.add(new MapRoute(City.ATLANTA, City.NEW_ORLEANS, 4, R.color.train_yellow));
        routes.add(new MapRoute(City.ATLANTA, City.RALEIGH, 2, R.color.train_grey));
        routes.add(new MapRoute(City.ATLANTA, City.RALEIGH, 2, R.color.train_grey));
        routes.add(new MapRoute(City.BOSTON, City.MONTREAL, 2, R.color.train_grey));
        routes.add(new MapRoute(City.BOSTON, City.MONTREAL, 2, R.color.train_grey));
        routes.add(new MapRoute(City.BOSTON, City.NEW_YORK, 2, R.color.train_red));
        routes.add(new MapRoute(City.BOSTON, City.NEW_YORK, 2, R.color.train_yellow));
        routes.add(new MapRoute(City.CALGARY, City.HELENA, 4, R.color.train_grey));
        routes.add(new MapRoute(City.CALGARY, City.SEATTLE, 4, R.color.train_grey));
        routes.add(new MapRoute(City.CALGARY, City.VANCOUVER, 3, R.color.train_grey));
        routes.add(new MapRoute(City.CALGARY, City.WINNIPEG, 6, R.color.train_white));
        routes.add(new MapRoute(City.CHARLESTON, City.MIAMI, 4, R.color.train_pink)); // pin
        routes.add(new MapRoute(City.CHARLESTON, City.RALEIGH, 2, R.color.train_grey));
        routes.add(new MapRoute(City.CHICAGO, City.DULUTH, 3, R.color.train_red));
        routes.add(new MapRoute(City.CHICAGO, City.OMAHA, 4, R.color.train_blue));
        routes.add(new MapRoute(City.CHICAGO, City.PITTSBURGH, 3, R.color.train_black));
        routes.add(new MapRoute(City.CHICAGO, City.PITTSBURGH, 3, R.color.train_orange)); // orange
        routes.add(new MapRoute(City.CHICAGO, City.ST_LOUIS, 2, R.color.train_green));
        routes.add(new MapRoute(City.CHICAGO, City.ST_LOUIS, 2, R.color.train_white));
        routes.add(new MapRoute(City.CHICAGO, City.TORONTO, 4, R.color.train_white));
        routes.add(new MapRoute(City.DALLAS, City.EL_PASO, 4, R.color.train_red));
        routes.add(new MapRoute(City.DALLAS, City.HOUSTON, 1, R.color.train_grey));
        routes.add(new MapRoute(City.DALLAS, City.HOUSTON, 1, R.color.train_grey));
        routes.add(new MapRoute(City.DALLAS, City.LITTLE_ROCK, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DALLAS, City.OKLAHOMA_CITY, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DALLAS, City.OKLAHOMA_CITY, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DENVER, City.HELENA, 4, R.color.train_green));
        routes.add(new MapRoute(City.DENVER, City.KANSAS_CITY, 4, R.color.train_black));
        routes.add(new MapRoute(City.DENVER, City.KANSAS_CITY, 4, R.color.train_orange)); // orange
        routes.add(new MapRoute(City.DENVER, City.OKLAHOMA_CITY, 4, R.color.train_red));
        routes.add(new MapRoute(City.DENVER, City.OMAHA, 4, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.DENVER, City.PHOENIX, 5, R.color.train_white));
        routes.add(new MapRoute(City.DENVER, City.SALT_LAKE_CITY, 3, R.color.train_red));
        routes.add(new MapRoute(City.DENVER, City.SALT_LAKE_CITY, 3, R.color.train_yellow));
        routes.add(new MapRoute(City.DENVER, City.SANTA_FE, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DULUTH, City.HELENA, 6, R.color.train_orange)); // orange
        routes.add(new MapRoute(City.DULUTH, City.OMAHA, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DULUTH, City.OMAHA, 2, R.color.train_grey));
        routes.add(new MapRoute(City.DULUTH, City.SAULT_ST_MARIE, 3, R.color.train_grey));
        routes.add(new MapRoute(City.DULUTH, City.TORONTO, 6, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.DULUTH, City.WINNIPEG, 4, R.color.train_black));
        routes.add(new MapRoute(City.EL_PASO, City.HOUSTON, 6, R.color.train_green));
        routes.add(new MapRoute(City.EL_PASO, City.LOS_ANGELES, 6, R.color.train_black));
        routes.add(new MapRoute(City.EL_PASO, City.OKLAHOMA_CITY, 5, R.color.train_yellow));
        routes.add(new MapRoute(City.EL_PASO, City.PHOENIX, 3, R.color.train_grey));
        routes.add(new MapRoute(City.EL_PASO, City.SANTA_FE, 2, R.color.train_grey));
        routes.add(new MapRoute(City.HELENA, City.OMAHA, 5, R.color.train_red));
        routes.add(new MapRoute(City.HELENA, City.PORTLAND, 6, R.color.train_yellow));
        routes.add(new MapRoute(City.HELENA, City.SALT_LAKE_CITY, 3, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.HELENA, City.WINNIPEG, 4, R.color.train_blue));
        routes.add(new MapRoute(City.HOUSTON, City.NEW_ORLEANS, 2, R.color.train_grey));
        routes.add(new MapRoute(City.KANSAS_CITY, City.OKLAHOMA_CITY, 2, R.color.train_grey));
        routes.add(new MapRoute(City.KANSAS_CITY, City.OKLAHOMA_CITY, 2, R.color.train_grey)); // pink
        routes.add(new MapRoute(City.KANSAS_CITY, City.OMAHA, 1, R.color.train_grey));
        routes.add(new MapRoute(City.KANSAS_CITY, City.OMAHA, 1, R.color.train_grey));
        routes.add(new MapRoute(City.KANSAS_CITY, City.ST_LOUIS, 2, R.color.train_blue));
        routes.add(new MapRoute(City.KANSAS_CITY, City.ST_LOUIS, 2, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.LAS_VEGAS, City.LOS_ANGELES, 2, R.color.train_grey));
        routes.add(new MapRoute(City.LAS_VEGAS, City.SALT_LAKE_CITY, 3, R.color.train_orange)); //orange
        routes.add(new MapRoute(City.LITTLE_ROCK, City.NASHVILLE, 3, R.color.train_white));
        routes.add(new MapRoute(City.LITTLE_ROCK, City.NEW_ORLEANS, 3, R.color.train_green));
        routes.add(new MapRoute(City.LITTLE_ROCK, City.OKLAHOMA_CITY, 2, R.color.train_grey));
        routes.add(new MapRoute(City.LITTLE_ROCK, City.ST_LOUIS, 2, R.color.train_grey));
        routes.add(new MapRoute(City.LOS_ANGELES, City.PHOENIX, 3, R.color.train_grey));
        routes.add(new MapRoute(City.LOS_ANGELES, City.SAN_FRANCISCO, 3, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.LOS_ANGELES, City.SAN_FRANCISCO, 3, R.color.train_yellow));
        routes.add(new MapRoute(City.MIAMI, City.NEW_ORLEANS, 6, R.color.train_red));
        routes.add(new MapRoute(City.MONTREAL, City.NEW_YORK, 3, R.color.train_blue));
        routes.add(new MapRoute(City.MONTREAL, City.SAULT_ST_MARIE, 5, R.color.train_black));
        routes.add(new MapRoute(City.MONTREAL, City.TORONTO, 3, R.color.train_grey));
        routes.add(new MapRoute(City.NASHVILLE, City.PITTSBURGH, 4, R.color.train_yellow));
        routes.add(new MapRoute(City.NASHVILLE, City.RALEIGH, 3, R.color.train_black));
        routes.add(new MapRoute(City.NASHVILLE, City.ST_LOUIS, 2, R.color.train_grey));
        routes.add(new MapRoute(City.NEW_YORK, City.PITTSBURGH, 2, R.color.train_green));
        routes.add(new MapRoute(City.NEW_YORK, City.PITTSBURGH, 2, R.color.train_white));
        routes.add(new MapRoute(City.NEW_YORK, City.WASHINGTON, 2, R.color.train_black));
        routes.add(new MapRoute(City.NEW_YORK, City.WASHINGTON, 2, R.color.train_orange));
        routes.add(new MapRoute(City.OKLAHOMA_CITY, City.SANTA_FE, 3, R.color.train_blue));
        routes.add(new MapRoute(City.PHOENIX, City.SANTA_FE, 3, R.color.train_grey));
        routes.add(new MapRoute(City.PITTSBURGH, City.RALEIGH, 2, R.color.train_grey));
        routes.add(new MapRoute(City.PITTSBURGH, City.ST_LOUIS, 5, R.color.train_green));
        routes.add(new MapRoute(City.PITTSBURGH, City.TORONTO, 2, R.color.train_grey));
        routes.add(new MapRoute(City.PITTSBURGH, City.WASHINGTON, 2, R.color.train_grey));
        routes.add(new MapRoute(City.PORTLAND, City.SALT_LAKE_CITY, 6, R.color.train_blue));
        routes.add(new MapRoute(City.PORTLAND, City.SAN_FRANCISCO, 5, R.color.train_green));
        routes.add(new MapRoute(City.PORTLAND, City.SAN_FRANCISCO, 5, R.color.train_pink)); // pink
        routes.add(new MapRoute(City.PORTLAND, City.SEATTLE, 1, R.color.train_grey));
        routes.add(new MapRoute(City.PORTLAND, City.SEATTLE, 1, R.color.train_grey));
        routes.add(new MapRoute(City.RALEIGH, City.WASHINGTON, 2, R.color.train_grey));
        routes.add(new MapRoute(City.RALEIGH, City.WASHINGTON, 2, R.color.train_grey));
        routes.add(new MapRoute(City.SALT_LAKE_CITY, City.SAN_FRANCISCO, 5, R.color.train_orange)); // orange
        routes.add(new MapRoute(City.SALT_LAKE_CITY, City.SAN_FRANCISCO, 5, R.color.train_white));
        routes.add(new MapRoute(City.SAULT_ST_MARIE, City.TORONTO, 2, R.color.train_grey));
        routes.add(new MapRoute(City.SAULT_ST_MARIE, City.WINNIPEG, 6, R.color.train_grey));
        routes.add(new MapRoute(City.SEATTLE, City.VANCOUVER, 1, R.color.train_grey));
        routes.add(new MapRoute(City.SEATTLE, City.VANCOUVER, 1, R.color.train_grey));

        for (MapRoute route : routes) {
            routeMap.put(new Pair<>(route.getStart().getKey(), route.getStop().getKey()), route);
        }
    }

    private MapRoute(City start, City stop, Integer length, Integer color) {
        this.color = color;
        this.length = length;
        this.start = start;
        this.stop = stop;
    }

    private MapRoute copy() {
        return new MapRoute(start, stop, length, color);
    }

    public static Map<Pair<String, String>, MapRoute> copyRouteMap() {
        Map<Pair<String, String>, MapRoute> routeMapCopy = new HashMap<>();
        for (Map.Entry<Pair<String, String>, MapRoute> entry : routeMap.entrySet()) {
            routeMapCopy.put(entry.getKey(), entry.getValue().copy());
        }
        return routeMapCopy;
    }

    public Integer getBackground() {
        switch (color) {
            case R.color.train_white:
            case R.color.train_yellow:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    public Integer getColor() {
        return color;
    }

    public Integer getLength() {
        return length;
    }

    public LatLng getMidpoint() {
        Double latitude = (start.getLatitude() + stop.getLatitude()) / 2;
        Double longitude = (start.getLongitude() + stop.getLongitude()) / 2;
        return new LatLng(latitude, longitude);
    }

    public static Map<Pair<String, String>, MapRoute> getRouteMap() {
        return routeMap;
    }

    public City getStart() {
        return start;
    }

    public City getStop() {
        return stop;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
