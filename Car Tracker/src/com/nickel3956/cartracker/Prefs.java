package com.nickel3956.cartracker;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
	private static final String OPT_FUEL = "fuel";
	private static final String OPT_DIST = "distance";
	private static final String OPT_ENTRY = "entry";
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addPreferencesFromResource(R.xml.settings);
	}
	
	
	
	public static String returnFuel(Context c)
	{
		return PreferenceManager.getDefaultSharedPreferences(c).getString(OPT_FUEL, "gallon");
	}
	
	public static String returnDist(Context c)
	{
		return PreferenceManager.getDefaultSharedPreferences(c).getString(OPT_DIST, "mile");
	}
	
	public static String returnEntry(Context c)
	{
		
		return PreferenceManager.getDefaultSharedPreferences(c).getString(OPT_ENTRY, "direct");
	}

}