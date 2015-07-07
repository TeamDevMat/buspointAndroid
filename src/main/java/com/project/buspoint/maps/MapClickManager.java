package com.project.buspoint.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import java.util.ArrayList;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapClickManager implements OnMapLongClickListener, OnMapClickListener{
	
	//que necesita esta clase?
	GoogleMap mMap;
	ArrayList<LatLng> myListOfPoints;

	public MapClickManager(GoogleMap gMap){
		
		myListOfPoints = new ArrayList<LatLng>();
		mMap = gMap;

		mMap.setOnMapLongClickListener(this);
		mMap.setOnMapClickListener(this);

	}

	@Override
	public void onMapLongClick(LatLng point){

		myListOfPoints.add(point);

		mMap.addMarker(new MarkerOptions()
        	.position(point)
        	.title("mi ultimo punto"));

	}

	@Override
	public void onMapClick(LatLng point){

	}
}