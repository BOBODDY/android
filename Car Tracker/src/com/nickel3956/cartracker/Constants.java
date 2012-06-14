package com.nickel3956.cartracker;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns{
	public static final String TABLE_NAME="cars";
	
	//Columns in the database
	public static final String YEAR = "year";
	public static final String MAKE = "make";
	public static final String MODEL = "model";
	public static final String ODOMETER  = "odometer";
	public static final String MILEAGE = "mileage";
}