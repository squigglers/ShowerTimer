//database things

package com.squigg1ers.showertimer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ShowerTimer.db";
    SQLiteDatabase db;
    Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Timer.SQL_CREATE_ENTRIES);
        db.execSQL(IntervalTimes.SQL_CREATE_ENTRIES);
    }

    //delete old table and make a new table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Timer.SQL_DELETE_ENTRIES);
        db.execSQL(IntervalTimes.SQL_DELETE_ENTRIES);

        onCreate(db);
    }

    //table stores ID and name of the Timer
    public static abstract class Timer implements BaseColumns {
        public static final String TABLE_NAME = "Timer";
        public static final String NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        NAME + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    //table stores the interval times for the Timer's ID
    public static abstract class IntervalTimes implements BaseColumns{
        public static final String TABLE_NAME = "IntervalTimes";
        public static final String TIMER_ID = "timer_id";
        public static final String INTERVAL_TIME = "interval_time"; //store time in 00:00 format

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        TIMER_ID + " INTEGER, " +
                        INTERVAL_TIME + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
