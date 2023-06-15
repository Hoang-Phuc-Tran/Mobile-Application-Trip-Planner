package com.example.tripplanner.mainproject;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.os.AsyncTask;

import com.example.tripplaner.mainproject.R;
import com.example.tripplaner.mainproject.databinding.TripDetailsBinding;
/*
 * Filename: tripDetails.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: March 17, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the trip details page
 */

public class tripDetails extends AppCompatActivity {
    private DBHandler dbHandler;
    Button stopButton = null;
    String hotel_name;
    String destination;
    String departure;
    String guest_num;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details);
        // Binding the views from the XML layout file to the activity
        TripDetailsBinding binding
                = DataBindingUtil.setContentView(this, R.layout.trip_details);

        // Initializing the database handler
        dbHandler = new DBHandler(tripDetails.this);

        // Getting references to views from the binding object
        TextView hotelView = binding.hotelName;
        TextView guestView = binding.numOfGuests;
        TextView departureView = binding.departureView;
        TextView destinationView = binding.destinationView;

        Intent intent = getIntent();

        // Retrieving the trip details from the intent
        hotel_name = intent.getStringExtra("keyOfHotel");
        destination = intent.getStringExtra("keyOfDestination");
        departure = intent.getStringExtra("keyOfDeparture");
        guest_num = intent.getStringExtra("keyOfGuest");
        date = intent.getStringExtra("keyOfDate");

        // Setting the trip details in the corresponding views
        Button clickBtn = binding.clickBtn;
        departureView.setText(departure);
        hotelView.setText(hotel_name);
        guestView.setText(guest_num);
        destinationView.setText(destination);

        // Preparing the AlertDialog for user confirmation
        Button btn_ok;
        View alerDialog = LayoutInflater.from(tripDetails.this).inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder alert_dialog = new AlertDialog.Builder(tripDetails.this);

        alert_dialog.setView(alerDialog);
        btn_ok = (Button) alerDialog.findViewById(R.id.button_ok);

        final AlertDialog dialog = alert_dialog.create();
        final Intent serviceIntent = new Intent(this, MyService.class);

        // Setting the OnClickListener for the clickBtn
        clickBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(tripDetails.this, guest_num, Toast.LENGTH_SHORT).show();


                // after adding the data we are displaying a toast message.
                Toast.makeText(tripDetails.this, "trip detail has been saved.", Toast.LENGTH_SHORT).show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        stopService(serviceIntent);

                        new MyThread().execute();
                        // Starting the MainActivity and clearing the input fields
                        startActivity(new Intent(tripDetails.this, MainActivity.class));
                        departureView.setText("");
                        hotelView.setText("");
                        guestView.setText("");
                        destinationView.setText("");

                    }
                });
            }
        });

    }

    // AsyncTask class to handle database operations in the background
    class MyThread extends AsyncTask<String, Void, String> {
        private int internalCounter = 0;

        @Override
        protected void onPreExecute() {
            dbHandler.addHotelInfo(hotel_name, guest_num);
        }

        @Override
        protected String doInBackground(String... params) {
            dbHandler.addLocation(departure, destination);
            return String.valueOf(System.currentTimeMillis());
        }

        @Override
        protected void onPostExecute(String result) {
            dbHandler.addDate(date);
        }
    }


}
