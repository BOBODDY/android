package com.nickel3956.cartracker;

import static android.provider.BaseColumns._ID;
import static com.nickel3956.cartracker.Constants.MAKE;
import static com.nickel3956.cartracker.Constants.MODEL;
import static com.nickel3956.cartracker.Constants.ODOMETER;
import static com.nickel3956.cartracker.Constants.TABLE_NAME;
import static com.nickel3956.cartracker.Constants.YEAR;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CarList extends ListActivity implements OnItemLongClickListener{
	private CarData cars;
	
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.carlist);
		cars = new CarData(this);
		try {
			Cursor cursor = getCars();
			showCars(cursor);
		} finally {
			cars.close();
		}
		
		ListView list = (ListView)findViewById(android.R.id.list);
		list.setOnItemLongClickListener(this);
		list.setOnItemClickListener(listen);
		
	}
	
	public void onResume()
	{
		super.onResume();
		
		cars = new CarData(this);
		try {
			Cursor cursor = getCars();
			showCars(cursor);
		} finally {
			cars.close();
		}
		
		ListView list = (ListView)findViewById(android.R.id.list);
		list.setOnItemLongClickListener(this);
		list.setOnItemClickListener(listen);
	}
	
	public static long car_id;
	private OnItemClickListener listen = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int i,
				long id) {
			// TODO Auto-generated method stub
			//Log.d(TABLE_NAME, "clicked on " + id);
			car_id=id;
			Context c = getApplicationContext();
			startActivity(new Intent(c, ShowCar.class));
		}
		
	};
	
	@Override
	public boolean onItemLongClick(AdapterView<?> av, View v, int i, long id) {
		openDialog(id);
		return true;
	}
	
	private void openDialog(final long id) {
		new AlertDialog.Builder(this)
			.setTitle("Options")
			.setItems(R.array.options, 
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface,
								int i) {
							doAction(id,i);
						}
			})
			.show();
	}
	
	private void doAction(long id, int i) {
		Log.d("Mileage", "clicked on " + i);
		switch(i)
		{
			case 0: //First option in list
				removeCar(id);
				cars = new CarData(this);
				try {
					Cursor cursor = getCars();
					showCars(cursor);
				} finally {
					cars.close();
				}
				break;
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.listmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.menu_new:
			Intent i = new Intent(this,NewEditCar.class);
			finish();
			startActivity(i);
			return true;
		case R.id.menu_home:
			Intent j = new Intent(this,Start.class);
			finish();
			startActivity(j);
			return true;
		}
		return false;
	}
	
	private static String[] FROM = {_ID, YEAR, MAKE, MODEL, ODOMETER, };
	private static String ORDER_BY = YEAR + " ASC";
	private static int[] TO = {R.id.rowid, R.id.year,R.id.make,R.id.model,R.id.odo, };
	
	private Cursor getCars() {
		SQLiteDatabase db = cars.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private void showCars(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, FROM, TO);
		setListAdapter(adapter);
	}

	private void removeCar(long id)
	{
		SQLiteDatabase db = cars.getWritableDatabase();
		db.execSQL("DELETE FROM " + TABLE_NAME
					+ " WHERE "
					+ _ID + "='" + id + "'");
	}

}
