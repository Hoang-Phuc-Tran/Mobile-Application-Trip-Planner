/*
 * Filename: MyService.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the MyService class
 */

package com.example.tripplanner.mainproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.example.tripplaner.mainproject.R;

public class MyService extends Service {

    // Defining a constant for the custom broadcast action
    public final static String MY_BROADCAST = "com.example.tripplanner.mainproject.MY_BROADCAST";

    // Default constructor for the service
    public MyService() {
    }

    NotificationManager manager = null;


    @Override
    public void onCreate() {
        Log.d("Trip Planner:", "Service has been initiated");


        CharSequence tripTitle = "service for notification";
        CharSequence tripContentText = "this is a service for notification";
        int trip_image = R.drawable.andorid_image;

        // Building the notification
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(trip_image)
                .setContentTitle(tripTitle)
                .setContentText(tripContentText)
                .setAutoCancel(true)
                .build();

        // Getting the NotificationManager system service and displaying the notification
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notification);

        ConnectivityManager conManager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            Log.d("Trip Planner","Network condition is good");
        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Trip Planner", "Trip Planner Service Started with id=" + startId);

        Intent broadcastIntent = new Intent(MY_BROADCAST);
        broadcastIntent.putExtra("MyData", "Hi from My Trip Planner");
        sendBroadcast(broadcastIntent);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d("Trip Planner", "my Service has been distroyed");
        manager.cancel(1);
    }



}