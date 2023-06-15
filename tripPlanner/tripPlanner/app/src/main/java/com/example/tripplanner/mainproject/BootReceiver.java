/*
 * Filename: BootReceiver.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the Boot Receiver class
 */

package com.example.tripplanner.mainproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// This method is called when the BOOT_COMPLETED event is received
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context _context, Intent myIntent) {
        // Log a message to indicate that the BOOT_COMPLETED event has been received
        Log.d("Trip Planner", "Boot Completed");
        // Create an intent to start the MyService class
        Intent tripServiceIntent = new Intent(_context, MyService.class);
        // Start the service using the intent
        _context.startService(tripServiceIntent);
    }
}
