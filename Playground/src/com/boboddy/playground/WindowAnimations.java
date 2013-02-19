package com.boboddy.playground;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WindowAnimations extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_animations);
        
        final Button defaultButton = (Button) findViewById(R.id.defaultButton);
        final Button translateButton = (Button) findViewById(R.id.translateButton);
        final Button scaleButton = (Button) findViewById(R.id.scaleButton);
        
        defaultButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent subActivity = new Intent(WindowAnimations.this, SubActivity.class);
				startActivity(subActivity);
				
			}
		});
        
        translateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent subActivity = new Intent(WindowAnimations.this, SubActivity.class);
				Bundle translateBundle = ActivityOptions.makeCustomAnimation(WindowAnimations.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
				startActivity(subActivity, translateBundle);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.window_animations, menu);
        return true;
    }
    
}
