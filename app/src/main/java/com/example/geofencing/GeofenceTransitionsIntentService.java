package com.example.geofencing;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import androidx.annotation.Nullable;

public class GeofenceTransitionsIntentService extends IntentService {
    private static final String TAG = "gfservice";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    // step 2 create a constructor and onCreate
    public GeofenceTransitionsIntentService(String name) {
//        Use this TAG to name the worker thread.
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    // step 1 implement method
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // use google documentation https://developer.android.com/training/location/geofencing#CreateAdd
        // step 3 get geo fencing data from the intent and check for errors.
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent); // 3a create object
        if(geofencingEvent.hasError()){
            // step 3b created a GeofenceErrorMessage class separately
            String errorMessage = GeofenceErrorMessage.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }
        // step 4a  Get the transition type. Enter (2), exit 2
         int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // 4b Test that the reported transition was of interest.
        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
            geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){
            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
        }
    }
}
