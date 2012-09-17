package com.nickel3956.cartracker;

import static android.provider.BaseColumns._ID;
import static com.nickel3956.cartracker.Constants.MAKE;
import static com.nickel3956.cartracker.Constants.MILEAGE;
import static com.nickel3956.cartracker.Constants.MODEL;
import static com.nickel3956.cartracker.Constants.ODOMETER;
import static com.nickel3956.cartracker.Constants.TABLE_NAME;
import static com.nickel3956.cartracker.Constants.YEAR;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowCar extends Activity implements OnClickListener{
	
	private CarData cars;
	public static Car myCar;
	long car_id = CarList.car_id;
	
	
	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.car);
		
		cars = new CarData(this);
		
		Cursor yearC = getyear();
		writeYear(yearC);
		Cursor makeC = getmake();
		writeMake(makeC);
		Cursor modelC = getmodel();
		writeModel(modelC);
		Cursor odoC = getodo();
		writeOdo(odoC);
		Cursor mpgC = getmpg();
		writeMpg(mpgC);
		
		Button addMpg = (Button)findViewById(R.id.car_mpgbutton);
		addMpg.setOnClickListener(this);
	}
	
	@Override
	public void onRestart() {
		super.onRestart();
		
		Cursor yearC = getyear();
		writeYear(yearC);
		Cursor makeC = getmake();
		writeMake(makeC);
		Cursor modelC = getmodel();
		writeModel(modelC);
		Cursor odoC = getodo();
		writeOdo(odoC);
		Cursor mpgC = getmpg();
		writeMpg(mpgC);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.car_mpgbutton:
				Context c = getApplicationContext();
				Intent i = new Intent(c, UpdateMpg.class);
				startActivity(i);
				break;
		}
		
	}
	
	private Cursor getyear() {
		SQLiteDatabase db = cars.getReadableDatabase();
		String id = car_id + "";
		String[] year = {_ID, YEAR, };
		Cursor cursor = db.query(TABLE_NAME, year, id, null, null, null, _ID + "");
		startManagingCursor(cursor);
		return cursor;
	}
	
	private Cursor getmake() {
		SQLiteDatabase db = cars.getReadableDatabase();
		String id = car_id+"";
		String[] make = {_ID, MAKE, };
		Cursor cursor = db.query(TABLE_NAME, make, id, null, null, null, _ID+"");
		startManagingCursor(cursor);
		return cursor;
	}
	
	private Cursor getmodel() {
		SQLiteDatabase db = cars.getReadableDatabase();
		String id = car_id+"";
		String[] model = {_ID, MODEL, };
		Cursor cursor = db.query(TABLE_NAME, model, id, null, null, null, _ID+"");
		startManagingCursor(cursor);
		return cursor;
	}
	
	private Cursor getodo() {
		SQLiteDatabase db = cars.getReadableDatabase();
		String id = car_id+"";
		String[] odo = {_ID, ODOMETER, };
		Cursor cursor = db.query(TABLE_NAME, odo, id, null, null, null, _ID+"");
		startManagingCursor(cursor);
		return cursor;
	}
	
	private Cursor getmpg() {
		SQLiteDatabase db = cars.getReadableDatabase();
		String id = car_id + "";
		String[] mpg = {_ID, MILEAGE, };
		Cursor cursor = db.query(TABLE_NAME, mpg, id, null, null, null, _ID+"");
		startManagingCursor(cursor);
		return cursor;
	}
	
	private void writeYear(Cursor cursor) {
		String year = "";
		
		while(cursor.moveToNext())
		{
			long thisId = cursor.getLong(0);
			if(car_id==thisId)
			{
				year=cursor.getString(1);
				break;
			}
			else
			{
				continue;
			}
		}
		
		TextView carYear = (TextView)findViewById(R.id.car_year);
		carYear.setText(year);
	}
	
	private void writeMake(Cursor cursor) {
		String make="";
		
		while(cursor.moveToNext())
		{
			long thisID = cursor.getLong(0);
			if(car_id==thisID)
			{
				make=cursor.getString(1);
				break;
			}
			else
			{
				continue;
			}
		}
		
		TextView carMake = (TextView)findViewById(R.id.car_make);
		carMake.setText(make);
	}
	
	private void writeModel(Cursor cursor) {
		String model="";
		
		while(cursor.moveToNext())
		{
			long thisID = cursor.getLong(0);
			if(car_id==thisID)
			{
				model = cursor.getString(1);
				break;
			}
			else
			{
				continue;
			}
		}
		
		TextView carModel = (TextView)findViewById(R.id.car_model);
		carModel.setText(model);
	}
	static String odo = "";
	private void writeOdo(Cursor cursor) {
		
		while(cursor.moveToNext())
		{
			long thisID = cursor.getLong(0);
			if(car_id==thisID)
			{
				odo = cursor.getString(1);
				break;
			}
			else
			{
				continue;
			}
		}
		
		if(odo.length()==0)
		{
			odo="0";
		}
		
		TextView carOdo = (TextView)findViewById(R.id.car_odo);
		carOdo.setText(odo);
	}
	public static String mpg = "";
	public static String mpgNum="";
	private void writeMpg(Cursor cursor) {
		
		while(cursor.moveToNext())
		{
			long thisID = cursor.getLong(0);
			if(car_id==thisID)
			{
				mpg = cursor.getString(1);
				break;
			}
			else
			{
				continue;
			}
		}
		
		if(mpg.length()==0)
		{
			mpg="0";
		}
		
		Context c = getApplicationContext();
    	boolean isGallon = Prefs.returnFuel(c).equalsIgnoreCase("gallons");
    	boolean isLiters = Prefs.returnFuel(c).equalsIgnoreCase("liters");
    	boolean isMiles = Prefs.returnDist(c).equalsIgnoreCase("miles");
    	boolean isKilos = Prefs.returnDist(c).equalsIgnoreCase("kilometers");
		
		TextView carMpg = (TextView)findViewById(R.id.car_mpg);
		TextView mileage = (TextView)findViewById(R.id.mpg);
		
		String unit = "";
		
		if(isMiles && isGallon)
		{
			mileage.setText("mpg");
			unit = "mpg";
		}
		else if(isMiles && isLiters)
		{
			mileage.setText("mpl");
			unit = "mpl";
		}
		else if(isKilos && isLiters)
		{
			mileage.setText("kpl");
			unit = "kpl";
		}
		else if(isKilos && isGallon)
		{
			mileage.setText("kpg");
			unit = "kpg";
		}
		carMpg.setText(mpg);
		mpgNum=mpg;
		mpg = mpg + unit;
	}
}
