package com.project.buspoint.activities;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.preference.PreferenceManager;

import com.project.buspoint.R;
import com.project.buspoint.maps.OnMapChangeTypeListener;

public class AppPreferencesActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{


	String LIST_PREFERENCE_KEY="pref_key_mapview";
	SharedPreferences sharedPreferences;

	OnMapChangeTypeListener mapChangeListener;

	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		addPreferencesFromResource(R.layout.preferences);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
        String key) {
        if (key.equals(LIST_PREFERENCE_KEY)) {
        	
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            connectionPref.setSummary(sharedPreferences.getString(key, ""));
            int MAP_TYPE = Integer.parseInt(sharedPreferences.getString("key",""));
            Toast.makeText(this,"D" + MAP_TYPE,Toast.LENGTH_SHORT).show();
            mapChangeListener.onChangeType(MAP_TYPE);
        }
    }

    @Override
	protected void onResume() {
    	super.onResume();
    	getPreferenceScreen().getSharedPreferences()
            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
    	super.onPause();
    	getPreferenceScreen().getSharedPreferences()
            .unregisterOnSharedPreferenceChangeListener(this);
	}



}