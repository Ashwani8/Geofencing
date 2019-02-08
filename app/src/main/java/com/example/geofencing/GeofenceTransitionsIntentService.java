package com.example.geofencing;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class GeofenceTransitionsIntentService extends IntentService {
    private static final String TAG = "gfservice";
    private Context context;
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
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // 4c Get the transition details as a String.
            // Get the transition details as a String.
            String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );
            // Send notification and log the transition details. step 5 create sendNotification helper method
           // this is where I need to add media code
            sendNotification(geofenceTransitionDetails);
            Log.i(TAG, geofenceTransitionDetails);
        }else {
            // Log the error.
            Log.e(TAG, getString(R.string.geofence_transition_invalid_type,
                    geofenceTransition));
        }
    }
    // 4d build the method to

    /**
     * Gets transition details and returns them as a formatted string.
     * @param context context
     * @param geofenceTransition The ID of the geofence transition.
     * @param triggeringGeofences The geofence(s) triggered.
     * @return  The transition details formatted as String.
     */
    private String getGeofenceTransitionDetails(
            Context context,
            int geofenceTransition,
            List<Geofence> triggeringGeofences) {
        String geofenceTransitionString = getTransitionString(geofenceTransition);

        // Get the Ids of each geofence that was triggered.
        ArrayList<String> triggeringGeofencesIdsList = new ArrayList<>();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString = TextUtils.join(", ",  triggeringGeofencesIdsList);

        return geofenceTransitionString + ": " + triggeringGeofencesIdsString;
    }

    private String getTransitionString(int geofenceTransition) {
    }
}
