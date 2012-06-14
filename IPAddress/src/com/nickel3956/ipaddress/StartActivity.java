package com.nickel3956.ipaddress;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StartActivity extends Activity {
    
	TextView ipString;
	
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        
        ipString = (TextView)findViewById(R.id.IP);
    }
    
    public void onResume() {
    	super.onResume();
    	
    	String IP = getIP();
    	ipString.setText(IP);
    }
    
	/*
	 * Toast.makeText(getApplicationContext(), text_to_show, Toast.length).show();
	 */
	private String getIP() {
		
		return "";
	}
}