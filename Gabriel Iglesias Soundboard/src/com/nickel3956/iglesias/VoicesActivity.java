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

public class VoicesActivity extends Activity {
	private static int voices[] = {R.raw.are_you_the_one_ringing_that_bell, R.raw.chicos, R.raw.hello_sir_hiiiiii, R.raw.hes_a_sexy_bitch, R.raw.i_hear_the_bell, R.raw.i_say_we_must_go_try_chicos, R.raw.iglasias_with_a_i, R.raw.im_gonna_go_to_jail, R.raw.my_grandma_lived_to_be_100_years_old, R.raw.nacho_libre_is_tripping, R.raw.oh_my_god_yes, R.raw.thank_you_very_much, R.raw.what_the_hell_is_chicos_australian, R.raw.what_the_hell_you_want, R.raw.whats_the_i_stand_for, R.raw.you_know_what_time_it_is, R.raw.cool_transition, R.raw.pop_rocks,R.raw.ubelievable,R.raw.back_to_whoring};
	private static int buttons[] = {R.id.voices_1,R.id.voices_2,R.id.voices_3,R.id.voices_4,R.id.voices_5,R.id.voices_6,R.id.voices_7,R.id.voices_8,R.id.voices_9,R.id.voices_10,R.id.voices_11,R.id.voices_12,R.id.voices_13,R.id.voices_14,R.id.voices_15,R.id.voices_16, R.id.voices_17,R.id.voices_18, R.id.voices_19,R.id.voices_20};
	String APP_NAME="Gabriel Iglesias Soundboard";
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.voices);
		
		
		String extStorageDir = Environment.getExternalStorageDirectory().toString();
		String basepath = extStorageDir + "/media";
		File file = new File(basepath+"/ringtone/");
		if(file.exists()){
			setFiles();
		}else{
			file.mkdirs();
			setFiles();
		}
		
		//setFiles();
		
		
	}
	
	protected void onResume() {
		super.onResume();
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
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
			String APP_NAME="Gabriel Iglesias Soundboard";
			Context c = getApplicationContext();
			switch(resId) {
				case R.id.voices_1:
					Log.d(APP_NAME, "sound played: " + voices[0]);
					Music.play(c, voices[0]);
					break;
				case R.id.voices_2:
					Log.d(APP_NAME, "sound played: " + voices[1]);
					Music.play(c, voices[1]);
					break;
				case R.id.voices_3:
					Log.d(APP_NAME, "sound played: " + voices[2]);
					Music.play(c, voices[2]);
					break;
				case R.id.voices_4:
					Log.d(APP_NAME, "sound played: " + voices[3]);
					Music.play(c, voices[3]);
					break;
				case R.id.voices_5:
					Log.d(APP_NAME, "sound played: " + voices[4]);
					Music.play(c, voices[4]);
					break;
				case R.id.voices_6:
					Log.d(APP_NAME, "sound played: " + voices[5]);
					Music.play(c, voices[5]);
					break;
				case R.id.voices_7:
					Log.d(APP_NAME, "sound played: " + voices[6]);
					Music.play(c, voices[6]);
					break;
				case R.id.voices_8:
					Log.d(APP_NAME, "sound played: " + voices[7]);
					Music.play(c, voices[7]);
					break;
				case R.id.voices_9:
					Log.d(APP_NAME, "sound played: " + voices[8]);
					Music.play(c, voices[8]);
					break;
				case R.id.voices_10:
					Log.d(APP_NAME, "sound played: " + voices[9]);
					Music.play(c, voices[9]);
					break;
				case R.id.voices_11:
					Log.d(APP_NAME, "sound played: " + voices[10]);
					Music.play(c, voices[10]);
					break;
				case R.id.voices_12:
					Log.d(APP_NAME, "sound played: " + voices[11]);
					Music.play(c, voices[11]);
					break;
				case R.id.voices_13:
					Log.d(APP_NAME, "sound played: " + voices[12]);
					Music.play(c, voices[12]);
					break;
				case R.id.voices_14:
					Log.d(APP_NAME, "sound played: " + voices[13]);
					Music.play(c, voices[13]);
					break;
				case R.id.voices_15:
					Log.d(APP_NAME, "sound played: " + voices[14]);
					Music.play(c, voices[14]);
					break;
				case R.id.voices_16:
					Log.d(APP_NAME, "sound played: " + voices[15]);
					Music.play(c, voices[15]);
					break;
				case R.id.voices_17:
					Log.d(APP_NAME, "sound played: " + voices[16]);
					Music.play(c, voices[16]);
					break;
				case R.id.voices_18:
					Log.d(APP_NAME, "sound played: " + voices[17]);
					Music.play(c, voices[17]);
					break;
				case R.id.voices_19:
					Log.d(APP_NAME, "sound played: " + voices[18]);
					Music.play(c, voices[18]);
					break;
				case R.id.voices_20:
					Log.d(APP_NAME, "sound played: " + voices[19]);
					Music.play(c, voices[19]);
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
					doAction(i,id);
				}
	})
	.show();
}

private void doAction(int i, final int id) {
	setRingtone(id);
}

private void setRingtone(int resId) {
	File newSoundFile = new File("");
	switch(resId) {
		case R.id.voices_1:
			newSoundFile=files[0];
			break;
		case R.id.voices_2:
			newSoundFile=files[1];
			break;
		case R.id.voices_3:
			newSoundFile=files[2];
			break;
		case R.id.voices_4:
			newSoundFile=files[3];
			break;
		case R.id.voices_5:
			newSoundFile=files[4];
			break;
		case R.id.voices_6:
			newSoundFile=files[5];
			break;
		case R.id.voices_7:
			newSoundFile=files[6];
			break;
		case R.id.voices_8:
			newSoundFile=files[7];
			break;
		case R.id.voices_9:
			newSoundFile=files[8];
			break;
		case R.id.voices_10:
			newSoundFile=files[9];
			break;
		case R.id.voices_11:
			newSoundFile=files[10];
			break;
		case R.id.voices_12:
			newSoundFile=files[11];
			break;
		case R.id.voices_13:
			newSoundFile=files[12];
			break;
		case R.id.voices_14:
			newSoundFile=files[13];
			break;
		case R.id.voices_15:
			newSoundFile=files[14];
			break;
		case R.id.voices_16:
			newSoundFile=files[15];
			break;
		case R.id.voices_17:
			newSoundFile=files[16];
			break;
		case R.id.voices_18:
			newSoundFile=files[17];
			break;
		case R.id.voices_19:
			newSoundFile=files[18];
			break;
		case R.id.voices_20:
			newSoundFile=files[19];
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
	 mCr.delete(uri, MediaStore.MediaColumns.DATA + "=\"" + newSoundFile.getAbsolutePath() + "\"",null);
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
public File files[]=new File[voices.length];
public void setFiles() {
	int count=0;
	for(int ij=0;ij<voices.length;ij++,count++) {
		File newSoundFile = new File("/sdcard/media/ringtone", "voice"+ij+".mp3");
		Uri mUri = Uri.parse("android.resource://com.nickel3956.iglesias/" + voices[ij]);
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
