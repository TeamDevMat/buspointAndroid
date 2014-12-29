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
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import  com.project.buspoint.R;

public class BusMapActivity extends Activity 
implements 
ConnectionCallbacks, 
OnConnectionFailedListener, 
LocationListener{


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
   	
	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( mLastLocation.getLatitude(), mLastLocation.getLongitude()),10));
	
	LocationRequest request = new LocationRequest().create();
	request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	request.setInterval(5000);
	request.setFastestInterval(1000);
	
	LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient,request,this);

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

   @Override
   public void onLocationChanged(Location location){
	mLastLocation = location;
	Toast.makeText(this,"Location Updated", Toast.LENGTH_SHORT).show();
   } 			
}
