package com.project.buspoint.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.location.Location;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import  com.project.buspoint.R;

public class BusMapActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener{


    public GoogleApiClient mGoogleClient;
    public Location mLastLocation;
    public GoogleMap mMap;	    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
	setContentView(R.layout.map);

	mMap =  ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	if(mMap == null)
		return;	

	buildGoogleApiClient();
	
   	
	
    }

    @Override
    protected void onStart(){
	super.onStart();
	mGoogleClient.connect();

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

	mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);
	Toast.makeText(this,"Lat:" + mLastLocation.getLatitude() + "Lng:" + mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
   	
	//mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( mLastLocation.getLatitude(), mLastLocation.getLongitude()),10));

	}


    @Override
    protected void onStop(){
	super.onStop();
	if(mGoogleClient.isConnected())
		mGoogleClient.disconnect();	
    }	
   @Override
   public void onConnectionSuspended(int cause){}	

   @Override
   public void onConnectionFailed(ConnectionResult arg0){
	
	
   } 			
}
