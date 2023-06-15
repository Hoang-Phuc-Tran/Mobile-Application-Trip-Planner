/*
 * Filename: ConnectivityReceiver.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the connectivity Receiver class
 */

package com.example.tripplanner.mainproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

// This method is called when the network connectivity changes
public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context tripPlannerContext, Intent tripIntent) {
        // Log a message to indicate that the network state has changed
        Log.d("Trip Planner", "Network State Changed");
        // Get the connectivity manager to check the network status
        ConnectivityManager tripManner = (ConnectivityManager)
                tripPlannerContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tripInformation = tripManner.getActiveNetworkInfo();

        // Create an intent to start the MyService class
        Intent tripService = new Intent(tripPlannerContext, MyService.class);

        // Check if there is an active network connection
        if (tripInformation != null && tripInformation.isConnected()) {
            Log.d("Trip planner","Connected - Start Service");
            tripPlannerContext.startService(tripService);
        } else {
            // Log a message to indicate that the device is disconnected from the network
            Log.d("Trip planner", "Disconnected - Stop Service");
            tripPlannerContext.stopService(tripService);
        }
    }
}

