package com.project.buspoint.activities;

import android.app.Activity;
import android.os.Bundle;

import  com.project.buspoint.R;

public class BusMapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map.xml);
    }
}
