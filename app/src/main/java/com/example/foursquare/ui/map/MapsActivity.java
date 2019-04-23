package com.example.foursquare.ui.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.foursquare.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        OnMapReadyCallback {


    private GoogleMap mMap;
    public Marker marker;
    String latitude;
    String longtitude;
    private static final float DEFAULT_ZOOM = 15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        latitude = list.get(0);
        longtitude = list.get(1);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng currentLocation = new LatLng(30,50);
        marker = mMap.addMarker(new MarkerOptions().position(currentLocation).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        moveCamera(currentLocation,DEFAULT_ZOOM);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                marker = mMap.addMarker(new MarkerOptions().position(point));
            }
        });
    }

    private void moveCamera(LatLng latLng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }

    @Override
    public void onMapClick(LatLng place) {
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }


}
