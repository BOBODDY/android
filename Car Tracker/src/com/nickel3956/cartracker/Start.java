package com.nickel3956.cartracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Start extends Activity implements OnClickListener{
	
	//Facebook fb = new Facebook("264872073530688");
	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.start);
		
//		Button mileage = (Button)findViewById(R.id.main_mpg);
//		mileage.setOnClickListener(this);
//		mileage.setEnabled(true);
//		Button settings = (Button)findViewById(R.id.main_settings);
//		settings.setOnClickListener(this);
//		settings.setEnabled(true);
//		Button cars = (Button)findViewById(R.id.main_cars);
//		cars.setOnClickListener(this);
//		cars.setEnabled(true);
//		Button nearMe = (Button)findViewById(R.id.main_near);
//		nearMe.setOnClickListener(this);
//		nearMe.setEnabled(true);
//		
//		TextView firstTime = (TextView)findViewById(R.id.firstTime);
//		
//		firstRunPreferences();
//		if(getFirstRun()) {
//			setRunned();
//			mileage.setEnabled(false);
//			cars.setEnabled(false);
//			nearMe.setEnabled(false);
//			settings.setEnabled(true);
//			firstTime.setVisibility(0);
//		}
	}
	
	protected void onResume() {
		super.onResume();
		
		Button mileage = (Button)findViewById(R.id.main_mpg);
		mileage.setOnClickListener(this);
		mileage.setEnabled(true);
		Button settings = (Button)findViewById(R.id.main_settings);
		settings.setOnClickListener(this);
		settings.setEnabled(true);
		Button cars = (Button)findViewById(R.id.main_cars);
		cars.setOnClickListener(this);
		cars.setEnabled(true);
		Button nearMe = (Button)findViewById(R.id.main_near);
		nearMe.setOnClickListener(this);
		nearMe.setEnabled(true);
		
		firstRunPreferences();
		if(getFirstRun()) {
			mileage.setEnabled(false);
			cars.setEnabled(false);
			nearMe.setEnabled(false);
			settings.setEnabled(true);
			setRunned();
		}
	}
	
	//Returns true if this is the first run
	public boolean getFirstRun() {
	    return mPrefs.getBoolean("firstRun", true);
	}
	
	public void setRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
	    edit.putBoolean("firstRun", false);
	    edit.commit();
	}
	
	SharedPreferences mPrefs;
	
	 public void firstRunPreferences() {
		    Context c = this.getApplicationContext();
		    mPrefs = c.getSharedPreferences("myAppPrefs", 0); //0 = mode private. only this app can read these preferences
	}
	
	
	@Override
	public void onRestart() {
		super.onRestart();
		
		Button mileage = (Button)findViewById(R.id.main_mpg);
		mileage.setOnClickListener(this);
		Button settings = (Button)findViewById(R.id.main_settings);
		settings.setOnClickListener(this);
		Button cars = (Button)findViewById(R.id.main_cars);
		cars.setOnClickListener(this);
		Button nearMe = (Button)findViewById(R.id.main_near);
		nearMe.setOnClickListener(this);
		
		mileage.setEnabled(true);
		cars.setEnabled(true);
		settings.setEnabled(true);
		nearMe.setEnabled(true);
		
		setRunned();
	}
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId()){
			case R.id.main_mpg:
				Intent mpg = new Intent(this, CalculateMileage.class);
				startActivity(mpg);
				break;
			case R.id.main_settings:
				Intent set = new Intent(this, Prefs.class);
				startActivity(set);
				break;
			case R.id.main_cars:
				Intent car = new Intent(this, CarList.class);
				startActivity(car);
				break;
			case R.id.main_near:
				Intent nearMe = new Intent(this, NearMe.class);
				startActivity(nearMe);
				break;
				
		}
	}

}
