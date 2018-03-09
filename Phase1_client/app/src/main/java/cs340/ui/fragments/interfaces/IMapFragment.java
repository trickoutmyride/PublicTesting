package cs340.ui.fragments.interfaces;

import android.util.Pair;

import java.util.Map;

import cs340.shared.model.MapRoute;

public interface IMapFragment {
    void onRouteClaimed(Map<Pair<String, String>, MapRoute> routes);
}
