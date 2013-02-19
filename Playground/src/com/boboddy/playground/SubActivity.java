package com.boboddy.playground;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends Activity{
	
	TextView mChargingStatus;
	TextView mBatteryLevel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subactivity);
		
		//The activity is being created
		mChargingStatus = (TextView) findViewById(R.id.battery_charging_status_text);
		mBatteryLevel = (TextView) findViewById(R.id.battery_level_text);
		
		this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		this.registerReceiver(this.mChargingInfoReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
		this.registerReceiver(this.mChargingInfoReceiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
	}
	
	@Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed"). aka Running
        
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
    
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
    	public void onReceive(Context c, Intent i) {
    		int level = i.getIntExtra("level", 0);
    		mBatteryLevel.setText("Battery level: "+String.valueOf(level)+"%");
    	}
    };
    
    private BroadcastReceiver mChargingInfoReceiver = new BroadcastReceiver() {
    	public void onReceive(Context c, Intent i) {
    		int chargingStatus = i.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    		
    		if(chargingStatus==BatteryManager.BATTERY_STATUS_CHARGING || 
    				chargingStatus==BatteryManager.BATTERY_PLUGGED_AC || 
    				chargingStatus==BatteryManager.BATTERY_PLUGGED_USB || 
    				chargingStatus==BatteryManager.BATTERY_PLUGGED_WIRELESS) {
    			mChargingStatus.setText("Charging status: Charging");
    		} else if(chargingStatus==BatteryManager.BATTERY_STATUS_DISCHARGING) {
    			mChargingStatus.setText("Charging status: Discharging");
    		} else if(chargingStatus==BatteryManager.BATTERY_STATUS_FULL) {
    			mChargingStatus.setText("Charging status: Full");
    		} else {
    			mChargingStatus.setText("Charging status: Unknown code ("+chargingStatus+")");
    		}
    	}
    };
}
