package com.project.buspoint.location;

import com.project.buspoint.location.DirtyLocationListener;
import android.util.Log;
import android.app.Dialog;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.location.Criteria;
import android.location.LocationManager;

import android.content.Context;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.os.Bundle;
import android.location.Location;
import android.widget.Toast;

/*Una clase que tiene como objetivo implemenar los servicios de localizacion*/
public class LocationApi implements 
ConnectionCallbacks, 
OnConnectionFailedListener,
LocationListener{
	
	Context context;
	GoogleApiClient mGoogleClient;
	Location myLastLocation;
	DirtyLocationListener dirtyLocationListener;

	public LocationApi(Context pContext,DirtyLocationListener dirty){
		context = pContext;
		dirtyLocationListener = dirty;
		buildGoogleApiClient();

	}

	public LocationApi(Context pContext){
		context = pContext;
		buildGoogleApiClient();

	}
	 protected synchronized void buildGoogleApiClient(){
	        mGoogleClient = new GoogleApiClient.Builder(context)
	                        .addConnectionCallbacks(this)
	                        .addOnConnectionFailedListener(this)
	                        .addApi(LocationServices.API)
	                        .build();

	    }

	public void connect(){
		mGoogleClient.connect();
	}

	public void disconnect(){
		mGoogleClient.disconnect();
	}

	public boolean isConnected(){
		return mGoogleClient.isConnected();
	}



 	@Override
    public void onConnected(Bundle connectionHint){

    	
    	myLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);
        
        if(myLastLocation != null){ 	
        	Toast.makeText(context,"Formalmente Contenctado", Toast.LENGTH_LONG).show();
        	
 		}else{
 			LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
 			Criteria criteria = new Criteria();
 			String prodiver = manager.getBestProvider(criteria,false);
 			myLastLocation = manager.getLastKnownLocation(prodiver);
 			//porque es null mylastlocation en el LG
 			Toast.makeText(context,myLastLocation.getLongitude() + "", Toast.LENGTH_LONG).show();
 		}

 		LocationRequest request = new LocationRequest().create();
        	
        	request.setInterval(50000);
        	request.setFastestInterval(10000);

        	LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient,request,this);

        	dirtyLocationListener.onDirtyConnected(myLastLocation);
     
       
    }
   
	@Override
	public void onLocationChanged(Location location){
       	dirtyLocationListener.onDirtyLocationChanged(location);
        Toast.makeText(context,"Posicion Actualizada", Toast.LENGTH_SHORT).show();
        
   	}

    @Override
   	public void onConnectionSuspended(int cause){

   	}       

	@Override
	public void onConnectionFailed(ConnectionResult arg0){


	}


	private boolean isGooglePlayServicesAvailable() {

        // Check that Google Play services is available
        return false;
    }

}