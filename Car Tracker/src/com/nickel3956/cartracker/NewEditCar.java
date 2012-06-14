package com.nickel3956.cartracker;

import static com.nickel3956.cartracker.Constants.MAKE;
import static com.nickel3956.cartracker.Constants.MILEAGE;
import static com.nickel3956.cartracker.Constants.MODEL;
import static com.nickel3956.cartracker.Constants.ODOMETER;
import static com.nickel3956.cartracker.Constants.TABLE_NAME;
import static com.nickel3956.cartracker.Constants.YEAR;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.net.*;

public class NewEditCar extends Activity implements OnClickListener{
	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.editcar);
	}
	
	protected void onResume() {
		super.onResume();
		
		Button save = (Button)findViewById(R.id.car_save);
		save.setOnClickListener(this);
		
		Button cancel = (Button)findViewById(R.id.car_cancel);
		cancel.setOnClickListener(this);
		
		ImageButton picture = (ImageButton)findViewById(R.id.imageButton1);
		picture.setOnClickListener(this);
	}
	
	public static Car thisCar;
	private CarData cars;
	private static final int IMAGE_REQUEST_CODE = 100, MEDIA_TYPE_IMAGE=1, SELECT_PICTURE_REQUEST_CODE = 10;
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.car_save:
			EditText y = (EditText)findViewById(R.id.yearEdit);
			EditText ma = (EditText)findViewById(R.id.makeEdit);
			EditText mo = (EditText)findViewById(R.id.modelEdit);
			EditText odo = (EditText)findViewById(R.id.odoEdit);
			
			String year = y.getText().toString();
			String make = ma.getText().toString();
			String model = mo.getText().toString();
			String odometer = odo.getText().toString();
			
			thisCar = new Car(year,make,model,odometer);
			cars = new CarData(this);
			
			String cyear = thisCar.returnYear();
			String cmake = thisCar.returnMake();
			String cmodel = thisCar.returnModel();
			String codo = thisCar.returnOdo();
			
			addCar(cyear, cmake, cmodel, codo);
			
			Intent i = new Intent(this, CarList.class);
			finish();
			startActivity(i);
			break;
		case R.id.car_cancel:
			Intent k = new Intent(this, CarList.class);
			finish();
			startActivity(k);
			break;
		case R.id.imageButton1: 
			doPicture();
		}
	}
	
	private void doPicture() {
		new AlertDialog.Builder(this)
		.setTitle("Picture")
		.setItems(R.array.camera_list, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface,
							int i) {
						switch(i) {
							case 0: //first option in list
								//new picture
								//start camera, startActivityForResult
								
								Uri uri;
								Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
								
								uri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
								intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
								
								Log.d("Car Tracker", "created intent, starting...");
								
								startActivityForResult(intent, IMAGE_REQUEST_CODE);
								break;
							case 1: //Second option
								//Pick from Gallery
								//open gallery, startActivityForResult
								
								Intent gallery = new Intent();
								gallery.setType("image/*");
								gallery.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(Intent.createChooser(gallery, "Select picture"), SELECT_PICTURE_REQUEST_CODE);
								break;
							default:
								//This shouldn't happen	
						}
					}
		})
		.show();
	}
	
	private static Uri getOutputMediaFileUri(int i) {
		return Uri.fromFile(getOutputMediaFile(i));
	}
	
	private static File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "CarTracker");
		
		//Create the storage director if it does not exist
		if(!mediaStorageDir.exists()) {
			if (! mediaStorageDir.mkdirs()){
	            Log.d("Car Tracker", "failed to create directory");
	            return null;
	        }
		}
		
		// Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else {
	        return null;
	    }
	    
	    Log.d("Car Tracker", "this is mediaFile: " + mediaFile.toString());

	    return mediaFile;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		ImageView carPicture = (ImageView)findViewById(R.id.carPic);
		
		Uri selectedImage;
		int VISIBLE = 0;
		
		if(resultCode == RESULT_OK) {
			if(requestCode == SELECT_PICTURE_REQUEST_CODE) {
				if(data!=null) {
					selectedImage = data.getData();
					String selectedImagePath = getPath(selectedImage);
					carPicture.setImageURI(selectedImage);
					carPicture.setVisibility(VISIBLE);
				}
			} else if(requestCode == IMAGE_REQUEST_CODE) {
				if(requestCode == IMAGE_REQUEST_CODE) {
					if(resultCode == RESULT_OK) {
						//Toast.makeText(getApplicationContext(), "Image saved to:\n" +
			              //       data.getData(), Toast.LENGTH_SHORT).show();
					} else if(resultCode == RESULT_CANCELED) {
						//User canceled
					} else {
						//something bad happened, inform user
						Toast.makeText(getApplicationContext(), "There was a problem, I'm working on it", Toast.LENGTH_SHORT).show();
					}
				}
			} else {
				Log.d("Car Tracker", "requestCode: " + requestCode + " resultCode: " + resultCode);
				Toast.makeText(getApplicationContext(), "Problem with data...", Toast.LENGTH_SHORT).show();
			}
			
			String [] projections = {
					MediaStore.Images.Thumbnails._ID,
					MediaStore.Images.Thumbnails.IMAGE_ID,
					MediaStore.Images.Thumbnails.KIND,
					MediaStore.Images.Thumbnails.DATA};
			String selection = MediaStore.Images.Thumbnails._ID + "=" + MediaStore.Images.Thumbnails.MINI_KIND;
			
			String sort = MediaStore.Images.Thumbnails._ID + " DESC";
			Cursor cursor = this.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, 
					projections, selection, null, sort);
			
			long imageId = 0l;
			long thumbnailImageId = 0l;
			String thumbnailPath = "";
			
			try {
				cursor.moveToFirst();
				imageId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID));
				thumbnailImageId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
				thumbnailPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
			} finally {
				cursor.close();
			}
			
			String[] largeFileProjection = { 
					MediaStore.Images.ImageColumns._ID,
					MediaStore.Images.ImageColumns.DATA};
			
			String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
			cursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, largeFileProjection, null, null, largeFileSort);
			String largeImagePath = "";
			
			try {
				cursor.moveToFirst();
				largeImagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
			} finally {
				cursor.close();
			}
			
			Uri largeImage = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(imageId));
			Uri thumbnailImage = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(thumbnailImageId));
			
			Log.d("Car Tracker", "i can haz uri: " + thumbnailImage.getPath());
			
			ImageView picture = (ImageView)findViewById(R.id.carPic);
			picture.setImageURI(largeImage);
			picture.setVisibility(VISIBLE);
			
		} else if(resultCode == RESULT_CANCELED) {
			//User canceled
		} else {
			//Something bad happened, inform the user
		}
	}
	
	public String getPath(Uri uri) {
		String[] projections = {MediaStore.Images.Media.DATA};
		Cursor cursor = managedQuery(uri, projections, null, null, null);
		int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(colIndex);
	}
	
	private void addCar(String y, String ma, String mo, String odo) {
		SQLiteDatabase db = cars.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(YEAR, y);
		values.put(MAKE, ma);
		values.put(MODEL, mo);
		values.put(ODOMETER, odo);
		values.put(MILEAGE, "");
		db.insertOrThrow(TABLE_NAME, null, values);
	}
}


