package com.nickel3956.tipcalc;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TipCalcActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RadioButton twenty = (RadioButton)findViewById(R.id.twenty);
        twenty.setChecked(true);
        
        Button start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText total = (EditText)findViewById(R.id.amount);
				double totalAmount;
				
				try
				{
					totalAmount = Double.parseDouble(total.getText().toString());
				}
				catch(NumberFormatException e)
				{
					totalAmount=0;
				}
				
				int tipAmount=1;
				
				RadioButton tip1 = (RadioButton)findViewById(R.id.fifteen);
				RadioButton tip2 = (RadioButton)findViewById(R.id.twenty);
				RadioButton tip3 = (RadioButton)findViewById(R.id.twentyfive);
				
				if(tip1.isChecked()==true)
				{
					tipAmount=15;
				}
				else if(tip2.isChecked()==true)
				{
					tipAmount=20;
				}
				else if(tip3.isChecked()==true)
				{
					tipAmount=30;
				}
				
				double tip = (double)totalAmount*((double)tipAmount/100);
				
				double finalTotal=totalAmount+tip;
				
				TextView tipView = (TextView)findViewById(R.id.tipAmount);
				TextView totalView = (TextView)findViewById(R.id.totalAmount);
				
				NumberFormat formatter = new DecimalFormat("0.00");
				
				String str1 = formatter.format(tip);
				String str2 = formatter.format(finalTotal);
				
				tipView.setText(str1);
				totalView.setText(str2);
			}
        	
        });
    }
}