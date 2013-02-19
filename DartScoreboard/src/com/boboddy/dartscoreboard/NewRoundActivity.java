package com.boboddy.dartscoreboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewRoundActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_round_dialog);
		
		Spinner spinner = (Spinner) findViewById(R.id.player1ScoreSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
											R.array.numbers, 
											android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_round_dialog, menu);
		return true;
	}

}
