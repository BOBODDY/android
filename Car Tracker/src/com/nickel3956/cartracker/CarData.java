package com.nickel3956.cartracker;

import static android.provider.BaseColumns._ID;
import static com.nickel3956.cartracker.Constants.MAKE;
import static com.nickel3956.cartracker.Constants.MODEL;
import static com.nickel3956.cartracker.Constants.ODOMETER;
import static com.nickel3956.cartracker.Constants.TABLE_NAME;
import static com.nickel3956.cartracker.Constants.YEAR;
import static com.nickel3956.cartracker.Constants.MILEAGE;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarData extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "cars.db";
	private static final int DATABASE_VERSION = 1;
	
	//Create a helper object for the Events database
	public CarData(Context c)
	{
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + YEAR + " TEXT NOT NULL, " + MAKE + " TEXT NOT NULL, " + MODEL + " TEXT NOT NULL, " + ODOMETER + " TEXT NOT NULL, " + MILEAGE + " TEXT NOT NULL);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Car car = NewEditCar.thisCar;
		String year=car.returnYear();
		String make=car.returnMake();
		String model=car.returnModel();
		String odo=car.returnOdo();
		String mpg = car.returnMpg();
		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (" + year + " TEXT NOT NULL, " + make + " TEXT NOT NULL, " + model + " TEXT NOT NULL, " + odo +" TEXT NOT NULL, " + mpg + " TEXT NOT NULL)");
	}
}