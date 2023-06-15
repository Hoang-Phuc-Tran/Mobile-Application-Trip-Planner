/*
 * Filename: MyBoroadcastReceiver.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the MyBroadcastReceiver class
 */

package com.example.tripplanner.mainproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    // This class is used to call the broadcastReceiver
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("MyBroadcastReceiver", "Your Battery is low");
        Toast.makeText(context, "Your Battery is low", Toast.LENGTH_LONG).show();
    }
}