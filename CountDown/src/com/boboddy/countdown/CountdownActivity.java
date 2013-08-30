package com.boboddy.countdown;

import java.util.Calendar;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CountdownActivity extends Activity {
	
	TextView countdown;
	ProgressDialog progressDialog;
	
	String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdown);
		// Show the Up button in the action bar.
		setupActionBar();
		
		countdown = (TextView)findViewById(R.id.countdown);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		this.date = MainActivity.chosenDate;
		//countdown.setText(this.date);
		
		String diff = getDifference(this.date);
		countdown.setText(diff);
	}
	
	private String getDifference(String end) {
		String diff = "time is weird",tmp[];
		
		tmp = end.split(" ");
		
		int month = convertMonth(tmp[0]);
		int day = Integer.valueOf(tmp[1].substring(0, tmp[1].length()-1));
		int year = Integer.valueOf(tmp[2]);
		
		//Calendar nowCal = Calendar.getInstance();
		Calendar thenCal = Calendar.getInstance();
		thenCal.set(year, month, day);
		
		DateTime now = DateTime.now();
		DateTime then = new DateTime(thenCal);
		
		long rightNow = now.getMillis();
		long rightThen = then.getMillis();
		
		long difference = rightThen-rightNow;
		
		thenCal = null;
		now = null;
		then = null;
		
		long seconds = difference/1000;
		
		int secInYear = 31536000;
		int secInMonth = 2628000;
		int secInDay = 86400;
		int secInHour = 3600;
		int secInMinute = 60;
		
		int numYears=0;
		int numMonths=0;
		int numDays=0;
		int numHours=0;
		int numMinutes=0;
		
		while(seconds>0) {
			if( (seconds-secInYear)>0 ) {
				seconds-=secInYear;
				numYears++;
			} else if ( (seconds-secInMonth)>0 ) {
				seconds-=secInMonth;
				numMonths++;
			} else if ( (seconds-secInDay)>0 ) {
				seconds-=secInDay;
				numDays++;
			} else if ( (seconds-secInHour)>0 ) {
				seconds-=secInHour;
				numHours++;
			} else if ( (seconds-secInMinute)>0 ) {
				seconds-=secInMinute;
				numMinutes++;
			}
		}
		
		diff = formatDiff(numYears,numMonths,numDays,numHours,numMinutes,(int)seconds);
		
		return diff;
	}
	
	private String formatDiff(int years, int months, int days, int hours, int minutes, int seconds) {
		String res="";
		
		if(years!=0)
			res += years+" years, ";
		if(months!=0)
			res += months+" months, ";
		if(days!=0)
			res += days+" days, ";
		if(hours!=0)
			res += hours+" hours, ";
		if(minutes!=0)
			res += minutes+" minutes, ";
		if(seconds!=0)
			res += seconds+" seconds";
		return res;
	}
	
	private int convertMonth(String month) {
		int val=-1;
		
		if(month.equals("January")) 
		{
			val = 0;
		}
		else if(month.equals("February")) {
			val = 1;
		}
		else if(month.equals("March")) {
			val = 2;
		}
		else if(month.equals("April")) {
			val = 3;
		}
		else if(month.equals("May")) {
			val = 4;
		}
		else if(month.equals("June")) {
			val = 5;
		}
		else if(month.equals("July")) {
			val = 6;
		}
		else if(month.equals("August")) {
			val = 7;
		}
		else if(month.equals("September")) {
			val = 8;
		}
		else if(month.equals("October")) {
			val = 9;
		}
		else if(month.equals("November")) {
			val = 10;
		}
		else if(month.equals("December")) {
			val = 11;
		}
		
		return val;
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.countdown, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
