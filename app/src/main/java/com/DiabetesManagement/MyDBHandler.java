package com.DiabetesManagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs all the database transactions.
 * This class is based on the examples displayed on the following website:
 * http://www.techotopia.com/index.php/An_Android_Studio_SQLite_Database_Tutorial
 * Last visit: 10/12/15.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    //REFERENCE FOR THIS CLASS: http://www.techotopia.com/index.php/An_Android_Studio_SQLite_Database_Tutorial

    private static final String ROUTINE_TABLE = "exercises_info";
    private static final String TABLE_EXERCISES = "exercises_done";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "uwlgymdb";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOAD = "load";
    public static final String COLUMN_REPETITIONS = "repetitions";
    public static final String COLUMN_DATE= "date";

    public static final String COLUMN_LOAD_UNIT="load_unit";
    public static final String COLUMN_REP_UNIT="repetition_unit";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
         createTables(db);
    }

    /**
     * performs the creaton of the databases, if they are not already in the system
     */
    public void createTables(SQLiteDatabase db){
        String CREATE_EXERCISES_TABLE = "CREATE TABLE " + TABLE_EXERCISES + "("  + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_LOAD + " INTEGER,"+ COLUMN_REPETITIONS + " INTEGER," + COLUMN_DATE + " DATE)";
        db.execSQL(CREATE_EXERCISES_TABLE);
        String CREATE_ROUTINE_TABLE = "CREATE TABLE " + ROUTINE_TABLE + "(" + COLUMN_NAME + " TEXT," + COLUMN_LOAD_UNIT+" TEXT,"+ COLUMN_REP_UNIT+ " TEXT)";
        db.execSQL(CREATE_ROUTINE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        //THIS PIECE OF CODE IS DELETING OUR DATABASE ON EVERY UPGRADE
        //NEED TO TO SOMETHING ABOUT IT
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE);
        onCreate(db);
    }

    /*
    performs the insertion of the new exercise to the routine_table
    that only stores the name and the load and repetition units
     */
    public void addExerciseOnRoutine( String ex_name, String ex_loadunit, String ex_repunit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ex_name);
        values.put(COLUMN_LOAD_UNIT, ex_loadunit);
        values.put(COLUMN_REP_UNIT, ex_repunit);
        db.insert(ROUTINE_TABLE, null, values);
        db.close();
    }

    /*
    performs the insertion of the new exercise made by the user
    recors the name, the load and repetitions done an finally the date.
     */
    public void addExercise(Exercise ex) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ex.get_name());
        values.put(COLUMN_LOAD, ex.get_load());
        values.put(COLUMN_REPETITIONS, ex.get_repetitions());
        values.put(COLUMN_DATE, ex.get_date());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }


    /*
    Verifies if the exercise name inserted by the user is already in the database
     */
    public boolean exerciseNameIsValid(String ex_name){
        String query = "SELECT * FROM " + ROUTINE_TABLE + " WHERE " + COLUMN_NAME + " = \'" + ex_name + "\'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0){
            return true;
        }
        else{
            return false;

        }
    }
    /*
    Search in the database for all the exercises names registered.
    This method is used to get the list of exercises into the spinners of the activities.
    */
    public List<String> getAllExercises(){
        String selectQuery = "SELECT "+COLUMN_NAME+" FROM " + ROUTINE_TABLE;
        List<String> exercises = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                exercises.add(cursor.getString(0));
                System.out.println(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning exercises
        return exercises;
    }

    //Performs the formatting and recreation of the tables
    public void format(){
       // String formatQuery = "DROP TABLE " + ROUTINE_TABLE+" , "+ TABLE_EXERCISES+";";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE);
        createTables(db);
    }
    /*
    Get the data stored about the desired exercise. This method is used
    by the StatisticsActivity to get the information about the exercises and then
    display in the graph.
    */
    public ArrayList<Statistics> getStatistics(String name){
        String selectQuery = "SELECT * FROM " +TABLE_EXERCISES+ " WHERE "+ COLUMN_NAME+"=\'"+name+"\'";
        ArrayList<Statistics> statistics = new ArrayList<Statistics>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int load = Integer.parseInt(cursor.getString(cursor.getColumnIndex("load")));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                Statistics st = new Statistics(load, date); //create a new Statistic
                statistics.add(st); //add to the list
                System.out.println(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return statistics;
    }
}
