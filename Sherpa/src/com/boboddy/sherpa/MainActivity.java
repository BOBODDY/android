package com.boboddy.sherpa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	Button discover;
	
	TextView string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		string = (TextView) findViewById(R.id.textView1);
		
		discover = (Button) findViewById(R.id.discover);
		discover.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Looking...", Toast.LENGTH_SHORT).show();
				try {
					URL url = new URL("https://10.3.1.101");
					new ConnectTask().execute(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

class ConnectTask extends AsyncTask<URL, Integer, String> {
	protected String doInBackground(URL...urls) {
		int count = urls.length;
		for(int i = 0; i < count; i++) {
			URL url = urls[i];
			
			try {
				HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
				urlConn.setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String arg0, SSLSession arg1) {
						return true;
					}
				});
				
				InputStream in = new BufferedInputStream(urlConn.getInputStream());
				urlConn.disconnect();
				byte buffer[] = new byte[in.available()];
				in.read(buffer);
				
				return new String(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "";
	}
}