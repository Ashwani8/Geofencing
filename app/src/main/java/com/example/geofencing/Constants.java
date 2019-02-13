package com.example.geofencing;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Constants used in this sample.
 */

final class Constants {
    private Constants(){
    }
    private static final String PACKAGE_NAME = "com.example.geofencing";

    static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";
    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    static final float GEOFENCE_RADIUS_IN_METERS = 200; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<>();

    static {
        // San Francisco International Airport.
//        BAY_AREA_LANDMARKS.put("SFO", new LatLng(37.621313, -122.378955));
        BAY_AREA_LANDMARKS.put("CSBwest", new LatLng(40.912690, -90.640615));

        // Googleplex.
        BAY_AREA_LANDMARKS.put("Huff", new LatLng(40.913461, -90.638435));
    }
}
