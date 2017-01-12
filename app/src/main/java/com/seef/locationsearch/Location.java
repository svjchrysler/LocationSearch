package com.seef.locationsearch;

import android.location.LocationListener;
import android.os.Bundle;

import com.seef.locationsearch.activities.MainActivity;

/**
 * Created by jhonsalguero on 1/9/17.
 */

public class Location implements LocationListener {

    private MainActivity mainActivity;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        mainActivity.setLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
