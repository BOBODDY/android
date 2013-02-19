package com.boboddy.playground;

import android.app.Activity;
import android.os.Bundle;

public class AnimatedSubActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subactivity);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}
