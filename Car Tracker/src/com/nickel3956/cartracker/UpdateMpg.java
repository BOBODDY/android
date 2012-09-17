package com.nickel3956.cartracker;

import static android.provider.BaseColumns._ID;
import static com.nickel3956.cartracker.Constants.MILEAGE;
import static com.nickel3956.cartracker.Constants.ODOMETER;
import static com.nickel3956.cartracker.Constants.TABLE_NAME;

import java.text.DecimalFormat;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateMpg extends Activity implements OnClickListener{
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.updatempg);
		
		Button save = (Button)findViewById(R.id.mpg_save);
		save.setOnClickListener(this);
		
		TextView inputTitle = (TextView)findViewById(R.id.mLabel);
		EditText inputBox = (EditText)findViewById(R.id.milesEdit);
		
		boolean isOdo = Prefs.returnEntry(this).equals("odo");
		boolean isDirect = Prefs.returnEntry(this).equals("direct");
		
		if(isOdo)
		{
			inputTitle.setText("New odometer reading");
			inputBox.setHint("Enter current odometer amount");
		}
		else if(isDirect)
		{
			inputTitle.setText("Enter distance traveled");
			inputBox.setHint("Distance traveled");
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		EditText dist = (EditText)findViewById(R.id.milesEdit);
		EditText fuel = (EditText)findViewById(R.id.fuelEdit);
		
		String odometer = dist.getText().toString();
		String fuelamount = fuel.getText().toString();
		
		updateCarMpg(odometer, fuelamount);
		finish();
	}
	
	public static Car thisCar;
	private CarData cars;
	long carid = CarList.car_id;
	
	private void updateCarMpg(String odo, String fuel) {
		
		boolean isOdo = Prefs.returnEntry(getApplicationContext()).equals("odo");
		boolean isDirect = Prefs.returnEntry(getApplicationContext()).equals("direct");
		
		//Context c = getApplicationContext();
		cars = new CarData(UpdateMpg.this);
		
		if(isOdo)
		{
			int oldOdo = Integer.parseInt(ShowCar.odo);
			int newOdo = Integer.parseInt(odo);
			int miles = newOdo - oldOdo;
			
			double gallons = Double.parseDouble(fuel);
			
			double newMpg = (double)miles/gallons;
			Log.d("Car", ShowCar.mpgNum);
			double oldMpg = Double.parseDouble(ShowCar.mpgNum);
			double mpg;
			if(oldMpg==0.0)
			{
				mpg = newMpg;
			}
			else
			{
				mpg = (double)((newMpg+oldMpg)/2);
			}
			DecimalFormat format = new DecimalFormat("#.###");
			String mileage = format.format(mpg);
			
			String id = carid+"";
			
			SQLiteDatabase db = cars.getWritableDatabase();
			db.execSQL("UPDATE " + TABLE_NAME + " SET " + ODOMETER + "='" + odo + "', " + MILEAGE + "='" + mileage + "' WHERE " + _ID + "='" + id + "'");
		}
		if(isDirect)
		{
			int oldOdo = Integer.parseInt(ShowCar.odo);
			
			double dist = Double.parseDouble(odo);
			double gallons = Double.parseDouble(fuel);
			
			double mpg = dist/gallons;
			double oldMpg = Double.parseDouble(ShowCar.mpgNum);
			if(oldMpg==0) {
				//nothing
			}
			else
			{
				mpg = (double)((mpg+oldMpg)/2);
			}
			DecimalFormat format = new DecimalFormat("#.###");
			String mileage = format.format(mpg);
			
			String id = carid+"";
			
			int a = Integer.parseInt(odo);
			int b = oldOdo;
			
			a+=b;
			
			odo = a + "";
			
			SQLiteDatabase db = cars.getWritableDatabase();
			db.execSQL("UPDATE " + TABLE_NAME + " SET " + ODOMETER + "='" + odo + "', " + MILEAGE + "='" + mileage + "' WHERE " + _ID + "='" + id + "'");
		}
		
	}

}
