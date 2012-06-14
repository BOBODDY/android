package com.nickel3956.iglesias;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TabHost;

public class SoundboardWidget extends TabActivity {
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.tabmain);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    intent = new Intent().setClass(this, VoicesActivity.class);
	    spec = tabHost.newTabSpec("voices").setIndicator("Voices",res.getDrawable(R.drawable.tab_voices_selector)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, SoundsActivity.class);
	    spec = tabHost.newTabSpec("sounds").setIndicator("Sounds",res.getDrawable(R.drawable.tab_sounds_selector)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, RandomActivity.class);
	    spec = tabHost.newTabSpec("random").setIndicator("Random", res.getDrawable(R.drawable.tab_random_selector)).setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(1);
	}
}
