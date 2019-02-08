package com.example.geofencing;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
        // step 3 get geo fencing data from the intent and check for errors.
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent); // 3a create object
        if(geofencingEvent.hasError()){
            // step 3b created a GeofenceErrorMessage class separately
            String errorMessage = GeofenceErrorMessage.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }
    }
}
