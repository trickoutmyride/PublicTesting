package cs340.ui.fragments;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.Map;

import cs340.shared.model.MapRoute;
import cs340.ui.R;
import cs340.ui.fragments.interfaces.IMapFragment;
import cs340.ui.presenters.MapPresenter;

public class GameMapFragment extends Fragment implements IMapFragment, OnMapReadyCallback {
    private GoogleMap map;
    private MapPresenter presenter;
    private Map<Polyline, MapRoute> routeLines = new HashMap<>();

    public GameMapFragment() {}

    private BitmapDescriptor createTextIcon(String text, Integer color, Integer background) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(color);
        paint.setTextSize(36f);
        paint.setTextAlign(Paint.Align.LEFT);
        Paint.FontMetrics metric = paint.getFontMetrics();
        Integer height = (int) Math.ceil(metric.descent - metric.ascent);
        Integer y = (int) (height - metric.descent);
        Integer width = (int) paint.measureText(text);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawColor(background);
        canvas.drawText(text, 0, y, paint);
        return BitmapDescriptorFactory.fromBitmap(image);
    }

    public MapPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.detach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(39, -98), 3);
        googleMap.animateCamera(cameraUpdate);
        map = googleMap;
        presenter = new MapPresenter(this);
        onRouteClaimed(MapRoute.getRouteMap());

        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {

            @Override
            public void onPolylineClick(Polyline polyline) {
                MapRoute route = routeLines.get(polyline);
                if (presenter != null && route != null) presenter.claimRoute(route);
            }
        });
    }

    @Override
    public void onRouteClaimed(Map<Pair<String, String>, MapRoute> routes) {
        map.clear();
        for (Map.Entry<Pair<String, String>, MapRoute> entry : routes.entrySet()) {
            MapRoute route = entry.getValue();
            Integer color = getResources().getColor(route.getColor());
            PolylineOptions line = new PolylineOptions()
                    .clickable(true)
                    .color(color)
                    .add(route.getStartLatLng())
                    .add(route.getStopLatLng());
            MarkerOptions label = new MarkerOptions()
                    .icon(createTextIcon(route.getLength().toString(), color, route.getBackground()))
                    .position(route.getMidpoint());
            Polyline polyline = map.addPolyline(line);
            map.addMarker(label);
            routeLines.put(polyline, route);
        }
    }
}
