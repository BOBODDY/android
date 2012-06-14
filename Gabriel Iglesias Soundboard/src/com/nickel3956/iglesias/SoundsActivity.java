package com.nickel3956.iglesias;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SoundsActivity extends Activity {
	private static int buttons[] = {R.id.sounds_1,R.id.sounds_2,R.id.sounds_3, R.id.sounds_4,R.id.sounds_5,R.id.sounds_6,R.id.sounds_7,R.id.sounds_8,R.id.sounds_9};
	private static int sounds[] = {R.raw.car_driving_and_brakes, R.raw.car_driving, R.raw.cop_siren, R.raw.crazy_laugh, R.raw.indian_sound, R.raw.scream, R.raw.stomach_growl_1, R.raw.stomach_growl_2, R.raw.suv_horn};
	private final String APP_NAME = "Gabriel Iglesias Soundboard";
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.sounds);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		String extStorageDir = Environment.getExternalStorageDirectory().toString();
		String basepath = extStorageDir + "/media";
		File file = new File(basepath+"/ringtone/");
		if(file.exists()){
			setFiles();
		}else{
			file.mkdirs();
			setFiles();
		}
	}
	
	protected void onResume() {
		super.onResume();
		for(int i=0;i<buttons.length;i++) {
			Button button = (Button)findViewById(buttons[i]);
			button.setOnClickListener(new ClickListener(button.getId()));
			button.setLongClickable(true);
			button.setOnLongClickListener(new LongListener(button.getId()));
		}
	}
	
	private class ClickListener implements OnClickListener {
		
		private int resId;
		
		public ClickListener(int id) {
			resId=id;
		}

		@Override
		public void onClick(View v) {
			Context c = getApplicationContext();
			switch(resId) {
				case R.id.sounds_1:
					Log.d(APP_NAME, "sound played: " + sounds[0]);
					Music.play(c, sounds[0]);
					break;
				case R.id.sounds_2:
					Log.d(APP_NAME, "sound played: " + sounds[1]);
					Music.play(c, sounds[1]);
					break;
				case R.id.sounds_3:
					Log.d(APP_NAME, "sound played: " + sounds[2]);
					Music.play(c, sounds[2]);
					break;
				case R.id.sounds_4:
					Log.d(APP_NAME, "sound played: " + sounds[3]);
					Music.play(c, sounds[3]);
					break;
				case R.id.sounds_5:
					Log.d(APP_NAME, "sound played: " + sounds[4]);
					Music.play(c, sounds[4]);
					break;
				case R.id.sounds_6:
					Log.d(APP_NAME, "sound played: " + sounds[5]);
					Music.play(c, sounds[5]);
					break;
				case R.id.sounds_7:
					Log.d(APP_NAME, "sound played: " + sounds[6]);
					Music.play(c, sounds[6]);
					break;
				case R.id.sounds_8:
					Log.d(APP_NAME, "sound played: " + sounds[7]);
					Music.play(c, sounds[7]);
					break;
				case R.id.sounds_9:
					Log.d(APP_NAME, "sound played: " + sounds[8]);
					Music.play(c, sounds[8]);
					break;
			}
		}
	}
	
	private class LongListener implements OnLongClickListener {
		private int resId;
		
		public LongListener(int id) {
			resId = id;
		}
		public boolean onLongClick(View v) {
			Log.d(APP_NAME,"resId: " + resId);
			openDialog(resId);
			return true;
		}
	}
	
	private void openDialog(final int id) {
		Log.d(APP_NAME, "id: " + id);
		new AlertDialog.Builder(this)
		.setTitle("Options")
		.setItems(R.array.options, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface,
							int i) {
						setRingtone(id);
					}
		})
		.show();
	}
	
	private void setRingtone(int resId) {
		File newSoundFile = new File("");
		switch(resId) {
			case R.id.sounds_1:
				newSoundFile=files[0];
				break;
			case R.id.sounds_2:
				newSoundFile=files[1];
				break;
			case R.id.sounds_3:
				newSoundFile=files[2];
				break;
			case R.id.sounds_4:
				newSoundFile=files[3];
				break;
			case R.id.sounds_5:
				newSoundFile=files[4];
				break;
			case R.id.sounds_6:
				newSoundFile=files[5];
				break;
			case R.id.sounds_7:
				newSoundFile=files[6];
				break;
			case R.id.sounds_8:
				newSoundFile=files[7];
				break;
			case R.id.sounds_9:
				newSoundFile=files[8];
				break;
		}
		
		ContentResolver mCr = getApplicationContext().getContentResolver();
		 
		Log.d(APP_NAME,"starting values");
		ContentValues values = new ContentValues();
		values.put(MediaStore.MediaColumns.DATA, newSoundFile.getAbsolutePath());
		values.put(MediaStore.MediaColumns.TITLE, "Gabriel Iglesias");
		values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
		values.put(MediaStore.MediaColumns.SIZE, newSoundFile.length());
		values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
		values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
		values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
		values.put(MediaStore.Audio.Media.IS_ALARM, true);
		values.put(MediaStore.Audio.Media.IS_MUSIC, false);
		
		Log.d(APP_NAME,"ringtone path:" + newSoundFile.getAbsolutePath());
		
		Uri uri = MediaStore.Audio.Media.getContentUriForPath(newSoundFile.getAbsolutePath());
		mCr.delete(uri, MediaStore.MediaColumns.DATA + "=\"" + newSoundFile.getAbsolutePath() + "\"", null);
		Uri newUri = mCr.insert(uri, values);
		Log.d(APP_NAME,"finished values");
		
		Log.d(APP_NAME,"setting ringtone");
		try {
		    RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE, newUri);
		} catch (Throwable t) {
		    Log.d(APP_NAME, "catch exception t: "+t);
		}
		Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
		Log.d(APP_NAME,"finished");
	}
	
	public File files[]=new File[sounds.length];
	public void setFiles() {
		int count=0;
		for(int ij=0;ij<sounds.length;ij++,count++) {
			File newSoundFile = new File("/sdcard/media/ringtone", "sound"+ij+".mp3");
			Uri mUri = Uri.parse("android.resource://com.nickel3956.iglesias/" + sounds[ij]);
			Log.d(APP_NAME,"file: "+newSoundFile);
			files[count]=newSoundFile;
			ContentResolver mCr = getApplicationContext().getContentResolver();
			AssetFileDescriptor soundFile;
			try {
				soundFile= mCr.openAssetFileDescriptor(mUri, "r");
			} catch (FileNotFoundException e) {
				soundFile=null;   
			}
			Log.d(APP_NAME, "soundfile: "+soundFile);
			try {
				byte[] readData = new byte[1024];
			    FileInputStream fis = soundFile.createInputStream();
			    FileOutputStream fos = new FileOutputStream(newSoundFile);
		        int i = fis.read(readData);

		        while (i != -1) {
		        	fos.write(readData, 0, i);
			        i = fis.read(readData);
			    }

			    fos.close();
			 } catch (IOException io) {
				 Log.d(APP_NAME,"IOException: " + io);
			 }
		}
	}
}
