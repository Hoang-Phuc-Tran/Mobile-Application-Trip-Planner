package com.example.tripplanner.mainproject;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplaner.mainproject.R;
import com.example.tripplaner.mainproject.databinding.HotelBookingBinding;
/*
 * Filename: hotelBooking.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the hotel booking
 */

public class hotelBooking extends MainActivity {
    public String selectedHotel ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_booking);

        //retreiving and declareing the value from flight booking
        Intent intent = getIntent();
        String destination = intent.getStringExtra("keyOfDestination");
        String departure = intent.getStringExtra("keyOfDeparture");
        String time = intent.getStringExtra("keyOfTime");
        String date = intent.getStringExtra("keyOfDate");

        //databinding
        HotelBookingBinding binding
                = DataBindingUtil.setContentView(this, R.layout.hotel_booking);

        TextView hotelHeader = binding.hotelHeader;
        hotelHeader.setText("HOTEL BOOKING:");

        //databinding for radio group
        RadioGroup radioGroup = binding.radioGroup;

        //databinding 5 radio selections
        RadioButton radio_btn_1 = binding.radioBtn1;
        radio_btn_1.setText("The Ritz-Carlton");

        RadioButton radio_btn_2 = binding.radioBtn2;
        radio_btn_2.setText("Radisson Hotel");

        RadioButton radio_btn_3 = binding.radioBtn3;
        radio_btn_3.setText("Holiday Inn");

        RadioButton radio_btn_4 = binding.radioBtn4;
        radio_btn_4.setText("Hilton Hotel");

        RadioButton radio_btn_5 = binding.radioBtn5;
        radio_btn_5.setText("Radisson Hotel");

        //databinding number of guests
        EditText people_counter = binding.peopleCounter;

        //databinding cancel & submit
        Button cancel_btn = binding.cancelBtn;
        cancel_btn.setText("CANCEL");
        Button submit_btn = binding.submitBtn;
        submit_btn.setText("NEXT");
        /*
         * Function: onClick
         * Description:This functios is listening to submit button
         * Parameter: no
         * Return Values: no return
         */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean  flag = true;

                String numOfGuest = people_counter.getText().toString();
                    int number = Integer.parseInt(numOfGuest);
                    if(number <= 0)
                    {
                        flag = false;
                        Toast.makeText(hotelBooking.this, "Error!! guest shall be natural number, please check your input", Toast.LENGTH_LONG).show();
                    }

                    if (radio_btn_1.isChecked()) {
                        selectedHotel = radio_btn_1.getText().toString();
                    } else if (radio_btn_2.isChecked()) {
                        selectedHotel = radio_btn_2.getText().toString();
                    } else if (radio_btn_3.isChecked()) {
                        selectedHotel = radio_btn_3.getText().toString();
                    } else if (radio_btn_4.isChecked()) {
                        selectedHotel = radio_btn_4.getText().toString();
                    } else if (radio_btn_5.isChecked()) {
                        selectedHotel = radio_btn_5.getText().toString();
                    }
                    else
                    {
                        flag = false;
                        Toast.makeText(hotelBooking.this, "Error!! one hotel must be selected, please select the hotel", Toast.LENGTH_LONG).show();
                    }

                    if(flag == true)
                    {
                        //passing data to tripdetail
                        Intent intent = new Intent(hotelBooking.this, tripDetails.class);
                        intent.putExtra("keyOfHotel",selectedHotel);
                        intent.putExtra("keyOfDestination",destination);
                        intent.putExtra("keyOfDeparture",departure);
                        intent.putExtra("keyOfGuest",numOfGuest);
                        intent.putExtra("keyOfDate",date);
                        intent.putExtra("keyOfTime",time);
                        startActivity(intent);
                    }
                }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(hotelBooking.this, MainActivity.class));
            }
        });
    }
}
