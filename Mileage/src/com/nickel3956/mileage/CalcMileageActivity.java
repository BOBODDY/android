package com.nickel3956.mileage;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalcMileageActivity extends Activity 
{
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.start);
		
		Button start = (Button)findViewById(R.id.calculate);
        start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				calculate();
			}
        	
        });
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.settings:
			startActivity(new Intent(this,Prefs.class));
			return true;
		}
		return false;
	}
    
    private void calculate()
    {
    	Context c = getApplicationContext();
    	boolean isGallon = Prefs.returnFuel(c).equalsIgnoreCase("gallons");
    	boolean isLiters = Prefs.returnFuel(c).equalsIgnoreCase("liters");
    	boolean isMiles = Prefs.returnDist(c).equalsIgnoreCase("miles");
    	boolean isKilos = Prefs.returnDist(c).equalsIgnoreCase("kilometers");
    	
    	EditText dist = (EditText)findViewById(R.id.milesEdit);
    	EditText fuel = (EditText)findViewById(R.id.gallonsEdit);
    	
    	if(dist.getText().toString().length()==0 && fuel.getText().toString().length()==0)
    	{
    		
    	}
    	double mpg=0.0;
    	if(isGallon && isMiles) //mpg
    	{
    		
    		if(dist.getText().toString().length()==0 && fuel.getText().toString().length()==0)
        	{
        		Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 mpg");

				AlertDialog alert = builder.create();

				alert.show(); 
        	}
    		else if(dist.getText().toString().length()==0 || fuel.getText().toString().length()==0)
    		{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 mpg");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    		else
    		{
    			double miles = Double.parseDouble(dist.getText().toString());
        		double gallons = Double.parseDouble(fuel.getText().toString());
        		
        		mpg = (double)miles/gallons;
        		
        		DecimalFormat format = new DecimalFormat("#.###");
				String value = format.format(mpg);
        		
        		Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n"+value+" mpg");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    		
    	}
    	if(isMiles && isLiters)
    	{
    		if(dist.getText().toString().length()==0 && fuel.getText().toString().length()==0)
        	{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 mpl");

				AlertDialog alert = builder.create();

				alert.show(); 
        	}
    		else if(dist.getText().toString().length()==0 || fuel.getText().toString().length()==0)
    		{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 mpl");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    		else
    		{
    			double miles = Double.parseDouble(dist.getText().toString());
        		double liters = Double.parseDouble(fuel.getText().toString());
        		
        		mpg = (double)miles/liters;
        		
        		DecimalFormat format = new DecimalFormat("#.###");
				String value = format.format(mpg);
        		
        		Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n"+value+" mpl");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    	}
    	if(isKilos && isLiters==true)
    	{
    		if(dist.getText().toString().length()==0 && fuel.getText().toString().length()==0)
        	{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 kpl");

				AlertDialog alert = builder.create();

				alert.show(); 
        	}
    		else if(dist.getText().toString().length()==0 || fuel.getText().toString().length()==0)
    		{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 kpl");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    		else
    		{
    			double kilometers = Double.parseDouble(dist.getText().toString());
        		double liters = Double.parseDouble(fuel.getText().toString());
        		
        		mpg = (double)kilometers/liters;
        		
        		DecimalFormat format = new DecimalFormat("#.###");
				String value = format.format(mpg);
        		
        		Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n"+value+" kpl");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    	}
    	if(isKilos && isGallon)
    	{
    		if(dist.getText().toString().length()==0 && fuel.getText().toString().length()==0)
        	{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 kpg");

				AlertDialog alert = builder.create();

				alert.show(); 
        	}
    		else if(dist.getText().toString().length()==0 || fuel.getText().toString().length()==0)
    		{
    			Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n0 kpg");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    		else
    		{
    			double kilometers = Double.parseDouble(dist.getText().toString());
        		double gallons = Double.parseDouble(fuel.getText().toString());
        		
        		mpg = (double)kilometers/gallons;
        		
        		DecimalFormat format = new DecimalFormat("#.###");
				String value = format.format(mpg);
        		
        		Context context = this;
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Your mileage is:\n"+value+" kpg");

				AlertDialog alert = builder.create();

				alert.show(); 
    		}
    	}
    }
}
