/*
 * Filename: MainActivity.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is a trip planning application utilizing java through android studio.
 */
package com.example.tripplanner.mainproject;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BuildConfig;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplaner.mainproject.R;
import com.example.tripplaner.mainproject.databinding.ActivityMainBinding;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    MyAppTest app = null;
    MyReceiver receiver1 = null;
    IntentFilter myFilter = null;
    private Button permissionBtn;
    private static final String TAG ="PERMISSION_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Broadcasts and receivers (system broadcast)
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_LOW");
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        registerReceiver(receiver, intentFilter);

        ActivityMainBinding bindingHeader
                = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //databinding for app header
        TextView appHeading = bindingHeader.appHeading;
        appHeading.setText("Trip Planner");

        final Intent serviceIntent = new Intent(this, MyService.class);

        //startplanning button databinding
        Button startPlanning = bindingHeader.startPlanning;
        startPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, flightBooking.class));
                startService(serviceIntent);
            }
        });

        //tripdetails button databinding
        Button startTripDetails = bindingHeader.startTripDetails;
        startTripDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tripDetails.class));
            }
        });

        // contact button databinding
        Button contactBtn = bindingHeader.contactInfo;
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, contactInfo.class));
            }
        });
        ///////////////////////////////////////////////////////////////
        // Handling application broadcast
        //////////////////////////////////////////////////////////////
        app = (MyAppTest) getApplication();

        myFilter = new IntentFilter(MyService.MY_BROADCAST);
        receiver1 = new MyReceiver();
        registerReceiver(receiver1, myFilter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

        ///////////////////////////////////////////////////////////////
        // Handling request permissions
        //////////////////////////////////////////////////////////////
        permissionBtn = findViewById(R.id.requestPermissions);

        permissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String permission = Manifest.permission.ACCESS_FINE_LOCATION;

                permissionLauncher.launch(permission);
            }
        });
    }

    private ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    Log.d(TAG, "onActivityResult: isGranted: "+ isGranted);

                    if (isGranted)
                    {
                        permissionGranted();
                    }
                    else
                    {
                        Log.d(TAG, "onActivityResult: Permission denied...");
                        Toast.makeText(MainActivity.this,"Permission denied...", Toast.LENGTH_SHORT).show();

                    }
                }
            }
    );

    private void permissionGranted()
    {
        Toast.makeText(MainActivity.this,"Permission granted...", Toast.LENGTH_SHORT).show();
    }

}