package com.nickel3956.mileage;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity{
	private static final String OPT_FUEL = "fuel";
	private static final String OPT_DIST = "distance";
	
	@Override
	protected void onCreate(Bundle bundle)
	{
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
}
