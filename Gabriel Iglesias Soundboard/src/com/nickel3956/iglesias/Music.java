package com.nickel3956.iglesias;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp = null;
	
	public static void play(Context c, int res) {
		stop(c);
		mp = MediaPlayer.create(c,res);
		mp.setLooping(false);
		mp.start();
	}
	
	public static void stop(Context c) {
		if(mp!=null) {
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}
