package com.boboddy.cricket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView counter;
	Button clickme, reset;
	
	int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        counter = (TextView)findViewById(R.id.counter);
        
        clickme = (Button)findViewById(R.id.clickme);
        clickme.setOnClickListener(new counterClick());
        
        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new resetClick());
        
        final Integer oldData = (Integer)getLastNonConfigurationInstance();
        if(oldData!=null) {
        	count = oldData.intValue();
        	counter.setText(""+count);
        } else {
        	count = 0;
        	counter.setText("0");
        }
    }
    
    public Object onRetainNonConfigurationInstance() {
    	return Integer.valueOf(count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private class counterClick implements OnClickListener {

		public void onClick(View v) {
			count++;
			counter.setText(""+count);
		}
    }
    
    private class resetClick implements OnClickListener {

		public void onClick(View v) {
			count = 0;
			counter.setText(""+count);
		}
    }
}
