/*
 * Filename: MyAppTest.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the source code that contains all the logic
 *  and functions for the MyAppTest class
 */

package com.example.tripplanner.mainproject;

import android.app.Application;

// This class is an Android application class that provides a variable to store application-level data.
public class MyAppTest extends Application
{
    public int getVar() {
        return var;
    }
    public void setVar(int var) {
        this.var = var;
    }
    private int var = 0;
}

