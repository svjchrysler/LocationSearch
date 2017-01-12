package com.seef.locationsearch.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seef.locationsearch.R;
import com.seef.locationsearch.models.Motel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private List<Motel> listMotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configInit();
    }

    private void configInit() {
        addMap();
    }

    private void configLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        com.seef.locationsearch.Location location = new com.seef.locationsearch.Location();
        location.setMainActivity(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (android.location.LocationListener) location);
    }

    private void addMap() {
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        configLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        List<Motel> listMotel = loadPoints();

        for(Motel motel : listMotel) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(motel.getLatitude(), motel.getLongitude()))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.marker))
                    .title(motel.getName())
                    .anchor(0.0f, 1.0f));
        }

        map.setOnMarkerClickListener(this);


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-17.782218, -63.179548), 13));
    }

    private List<Motel> loadPoints() {
        listMotels = new ArrayList<>();
        listMotels.add(new Motel("MOTEL CAPRI",  -17.75599685810015f, -63.19899132280972f, 3.3f, R.drawable.roomone));
        listMotels.add(new Motel("MOTEL LINDOS SUEÑOS",  -17.769449f, -63.144608f, 1.3f, R.drawable.roomtwo));
        listMotels.add(new Motel("MOTEL AVEK",  -17.771511097451288f, -63.190846188096046f, 2.0f, R.drawable.roomthree));
        listMotels.add(new Motel("MOTEL CHICKI CHIKI",  -17.8136832f, -63.1773574f, 4.2f, R.drawable.roomfour));
        return listMotels;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showDialog(marker);
        return false;
    }

    private void showDialog(Marker marker) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_info);
        TextView txtName = (TextView) dialog.findViewById(R.id.txtTitle);
        ImageView imgHeader = (ImageView)dialog.findViewById(R.id.imgHeader);
        RatingBar ratingBar = (RatingBar)dialog.findViewById(R.id.rating);
        Motel motel = getMotel(marker.getTitle());
        txtName.setText(motel.getName());
        imgHeader.setImageResource(motel.getPhotos());
        ratingBar.setRating(motel.getScore());
        dialog.show();
    }


    private Motel getMotel(String name) {
        for (Motel motel : listMotels) {
            if (motel.getName().equals(name))
                return motel;
        }
        return null;
    }

    private void addMarkerLocation(Location location) {
        map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    public void setLocation(Location location) {
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            addMarkerLocation(location);
        }
    }
}
