package com.example.tripplanner.mainproject;
/*
 * Filename: flightBooking.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the flight booking
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.tripplaner.mainproject.R;
import com.example.tripplaner.mainproject.databinding.FlightBookingBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class flightBooking extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.flight_booking);

        // Set databinding
        FlightBookingBinding bindingFlight
                = DataBindingUtil.setContentView(this, R.layout.flight_booking);

        //databinding for the text of destination
        TextView textDest = bindingFlight.textDest;
        textDest.setText("Selected Location");

        //databinding for the beginning of the flight
        TextView beginning = bindingFlight.beginning;
        beginning.setText("From :");

        //databinding for the destination of the flight
        TextView destination = bindingFlight.destination;
        destination.setText("To :");

        //databinding for the date text of the flight
        TextView txtdate = bindingFlight.txtDate;
        txtdate.setText("Selected Date and Time for departure");

        //databinding for the departure date of the flight
        TextView departureDate = bindingFlight.departureDate;
        departureDate.setText("Date :");

        //databinding for the time departure of the flight
        TextView departureTime = bindingFlight.departureTime;
        departureTime.setText("Time :");

        //databinding for the date of the flight
        Button btnPickDay = bindingFlight.btnPickDay;
        btnPickDay.setText("Choose Date");

        //databinding for the time departure of the flight
        Button btnPickTime = bindingFlight.btnPickTime;
        btnPickTime.setText("Choose Time");


        //databinding for displaying the date
        TextView editTextDate1 = bindingFlight.editTextDate1;

        // Initialize Calender object
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        /*
         * Function: onClick
         * Description:This functios is listening to choose date button
         * Parameter: no
         * Return Values: no return
         */
        btnPickDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        flightBooking.this, androidx.databinding.library.baseAdapters.R.style.Theme_AppCompat_DayNight_DialogWhenLarge,
                        setListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                datePickerDialog.show();
            }
        });

        /*
         * Function: onDataSet
         * Description:This functios gets value from user and display it
         * Parameter: no
         * Return Values: no return
         */
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth+"/"+month+"/"+year;
                editTextDate1.setText(date);
            }
        };


        //databinding for displaying the time
        TextView editTextTime = bindingFlight.editTextTime;


        //int minute, hour;

        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar1 = Calendar.getInstance();
                int hour = calendar1.get(calendar1.HOUR_OF_DAY);
                int minute = calendar1.get(calendar1.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(flightBooking.this,
                        androidx.databinding.library.baseAdapters.R.style.Theme_AppCompat_DayNight_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(calendar1.HOUR_OF_DAY, hourOfDay);
                                c.set(calendar1.MINUTE, hourOfDay);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                                String time = format.format(c.getTime());
                                editTextTime.setText(time);
                            }
                        }, hour, minute , false);
                timePickerDialog.show();
            }
        });

        //databinding for the time departure of the flight
        Button submitBtn = bindingFlight.submitBtn;
        submitBtn.setText("NEXT");
        TextView test = bindingFlight.test;

        Button backBtn = bindingFlight.backBtn;


        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.City, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // spinner for location From
        Spinner spinnerFrom = bindingFlight.spinnerFrom;
        spinnerFrom.setAdapter(adapter);

        // spinner for location To
        Spinner spinnerTo = bindingFlight.spinnerTo;
        spinnerTo.setAdapter(adapter);

        /*
         * Function: onClick
         * Description:This functios setting up string to the spinner
         * Parameter: no
         * Return Values: no return
         */

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String departure = spinnerFrom.getSelectedItem().toString();
                String destination = spinnerTo.getSelectedItem().toString();
                Toast.makeText(flightBooking.this,departure, Toast.LENGTH_LONG).show();
                boolean flag = true;
                //input validation
                    if(departure == destination) {
                        Toast.makeText(flightBooking.this, "Error!! departure and destination cannot be the same!!", Toast.LENGTH_LONG).show();
                        flag = false;
                    }
                    if(editTextDate1.getText().toString().equals("") || editTextTime.getText().toString().equals("")) {
                        Toast.makeText(flightBooking.this, "Error!! date and time are not selected", Toast.LENGTH_LONG).show();
                        flag = false;
                    }
                    if(flag == true)
                    {
                        //passing data to hotelbooking
                        Intent intent = new Intent(flightBooking.this, hotelBooking.class);
                        intent.putExtra("keyOfDeparture", departure);
                        intent.putExtra("keyOfDestination",destination);
                        intent.putExtra("keyOfDate",editTextDate1.getText().toString());
                        intent.putExtra("keyOfTime", editTextTime.getText().toString());
                        startActivity(intent);
                    }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(flightBooking.this, MainActivity.class));
            }
        });

    }
}
