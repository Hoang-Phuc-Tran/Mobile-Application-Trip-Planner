/*
 * Filename: MyReceiver.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the MyReceiver class
 */

package com.example.tripplanner.mainproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String myMessage = intent.getStringExtra("MyData");
        Log.d("MyApp", myMessage);
    }
}
