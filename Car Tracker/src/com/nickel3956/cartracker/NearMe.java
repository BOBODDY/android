package com.nickel3956.cartracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NearMe extends Activity {
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.nearme);
	}
	
	protected void onResume() {
		super.onResume();
		
		Button gas = (Button)findViewById(R.id.gas);
		gas.setOnClickListener(new ButtonListener());
	}
	
	private class ButtonListener implements OnClickListener {
		
		public void onClick(View v) {
			Intent nearMe = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=gas station"));
			startActivity(nearMe);
		}
	}

}
