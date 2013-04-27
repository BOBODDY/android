package com.boboddy.countdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity implements OnDateSetListener,OnClickListener {
	
	static String chosenDate;
	
	TextView date;
	Button getDate, start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Do something if the app hasn't been opened before
		boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun",true);
		if(firstRun) {
			//First time the app is opened
			new AlertDialog.Builder(this).setTitle("First run").setMessage("This is the first time the app has been opened").setNeutralButton("OK",null).show();
			getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putBoolean("firstRun",false).commit();
			
		}
		
		date = (TextView) findViewById(R.id.date);
		
		getDate = (Button) findViewById(R.id.dateGET);
		
		start = (Button) findViewById(R.id.start);
		start.setVisibility(View.INVISIBLE);
		start.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		Intent start = new Intent(this, CountdownActivity.class);
		startActivity(start);
	}
	
	public void openDatePickerDialog(View v) {
		DatePickerFragment df = new DatePickerFragment();
		df.show(getFragmentManager(), "datePicker");
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String chosenDate = getDate(month, day, year);
		
		MainActivity.chosenDate = chosenDate;
		date.setText(chosenDate);
		
		start.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private String getDate(int month, int day, int year) {
		String date="";
		
		String m="";
		
		if(month==0){
			m="January";
		} else if(month==1) {
			m="February";
		} else if(month==2) {
			m="March";
		} else if(month==3) {
			m="April";
		} else if(month==4) {
			m="May";
		} else if(month==5) {
			m="June";
		} else if(month==6) {
			m="July";
		} else if(month==7) {
			m="August";
		} else if(month==8) {
			m="September";
		} else if(month==9) {
			m="October";
		} else if(month==10) {
			m="November";
		} else if(month==11) {
			m="December";
		}
		
		date = m + " " + day + ", " + year;
		
		return date;
	}
}
