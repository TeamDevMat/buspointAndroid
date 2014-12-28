package com.project.buspoint.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationServices;

import  com.project.buspoint.R;

public class BusMapActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener{


    public GoogleApiClient mGoogleClient;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
   	buildGoogleApiClient();
	
    }


    protected synchronized void buildGoogleApiClient(){
	mGoogleClient = new GoogleApiClient.Builder(this)
			.addConnectionCallbacks(this)
			.addOnConnectionFailedListener(this)
			.addApi(LocationServices.API)
			.build();

    }

    @Override
    public void onConnected(Bundle connectionHint){
	Toast.makeText(this, "Connected to location services API",Toast.LENGTH_SHORT).show();	
   }


   @Override
   public void onDisconnected(){
	//nothing	
   }

   @Override
   public void onConnectionFailed(ConnectionResult arg0){
	
	
   } 			
}
