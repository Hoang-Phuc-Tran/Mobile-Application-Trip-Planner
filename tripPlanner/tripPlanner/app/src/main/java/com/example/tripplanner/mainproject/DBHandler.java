package com.example.tripplanner.mainproject;
/*
 * Filename: DBHandler.java
 * Project: Assignment 3 Trip Planner
 * Author: Bakr Jasim, Bum Su Yi, Philip Wojdyna, Phuc Tran
 * Date: April 15, 2023
 * Description: This is the database class that will handle the database of the Trip planner
 */

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "assignment2db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME1 = "hotel_info";
    private static final String TABLE_NAME2 = "location_info";
    private static final String TABLE_NAME3 = "date_info";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String DEPARTURE_COL = "departure";

    // below variable id for our course duration column.
    private static final String DESTINATION_COL = "destination";

    // below variable for our course description column.
    private static final String GUEST_NUM_COL = "guest_num";

    // below variable is for our course tracks column.
    private static final String HOTEL_COL = "hotel";
    private static final String DATE_COL = "date";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query1 = "CREATE TABLE " + TABLE_NAME1 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GUEST_NUM_COL + " TEXT,"
                + HOTEL_COL + " TEXT)";

        String query2 = "CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DEPARTURE_COL + " TEXT,"
                + DESTINATION_COL + " TEXT)";

        String query3 = "CREATE TABLE " + TABLE_NAME3 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT)";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    /*
     * Function: addHotelInfo
     * Description:This functios is adding hotel info to the database
     * Parameter: hotel_name: hotel name, guest_num: number of guests
     * Return Values: no return
     */
    public void addHotelInfo(String hotel_name,String guest_num) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GUEST_NUM_COL, guest_num);
        values.put(HOTEL_COL, hotel_name);

        db.insert(TABLE_NAME1, null, values);

        db.close();
    }
    /*
     * Function: addLocation
     * Description:This functios is adding location info to the database
     * Parameter: departure, destination
     * Return Values: no return
     */

    public void addLocation(String departure,String destination) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DEPARTURE_COL, departure);
        values.put(DESTINATION_COL, destination);

        db.insert(TABLE_NAME2, null, values);

        db.close();
    }
    /*
     * Function: addDate
     * Description:This functios is adding date info to the database
     * Parameter: date
     * Return Values: no return
     */

    public void addDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DATE_COL, date);


        db.insert(TABLE_NAME3, null, values);

        db.close();
    }
    /*
     * Function: onUpgrade
     * Description:This functios is updating the database
     * Parameter: db: database, old version, new version
     * Return Values: no return
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }
}
