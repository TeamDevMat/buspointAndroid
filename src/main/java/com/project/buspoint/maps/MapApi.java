package com.project.buspoint.maps;

import com.project.buspoint.R;
import com.project.buspoint.maps.OnMapChangeTypeListener;

import android.widget.Toast;
import java.util.List;
import com.google.android.gms.maps.model.PolylineOptions;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

import com.project.buspoint.location.DirtyLocationListener;
import com.project.buspoint.core.Coord;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.location.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

/*Esta clase controla todo lo que tenga que ver con los mapas de google */
public class MapApi implements DirtyLocationListener, 
							   OnMapLongClickListener,
							   OnMapChangeTypeListener
							   {	

	/*que necesito*/
	SharedPreferences sharedPref;
	String MAP_TYPE_DEFAULT = "1";
	String LIST_PREFERENCE_KEY="pref_key_mapview";

	Activity activity;
	GoogleMap gMap;
	Marker lastMarker;
	Marker droneMarker;
	Marker userMarker;
	public static int seq = 1;



	public MapApi(Activity pAct)
	{	activity = pAct;
		sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		int MAP_TYPE = Integer.parseInt(sharedPref.getString(LIST_PREFERENCE_KEY, MAP_TYPE_DEFAULT));
		
		gMap =   ((MapFragment)  activity.getFragmentManager().findFragmentById(R.id.map)).getMap();
		gMap.setOnMapLongClickListener(this);
		

		gMap.setMapType(MAP_TYPE);

	}

	public void onChangeType(int mapType){
		gMap.setMapType(mapType);
	}
	
	//detalles
	public void onDirtyLocationChanged(Location location){
		if(location != null){
			gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( location.getLatitude(), location.getLongitude()),15));
			
			userMarker("home",location.getLatitude(), location.getLongitude());
	}
	}
	public void onDirtyConnected(Location location){
		if(location != null)
		gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( location.getLatitude(), location.getLongitude()),16));
		//userMarker("home",location.getLatitude(), location.getLongitude());
     }

    @Override
    public void onMapLongClick(LatLng latlng){

    	
    }

    

    //este debe ser una interfaz.
    public void pintarRuta(List<Coord> coords){
    	//pintar polyline.
    	//1.crear un objeto polylineOptions.
    	//2.Usar el ciclo for para recorrer la lista coords de tamanio coords.length.
    		//3. Para cada iteracion de ciclo obtener el elemento i de la lista.
    		//4. Agregar una coordenada para cada interacion i usando el elemento que obtenido.

    	PolylineOptions options = new PolylineOptions();

    	for(int i=0; i < coords.size();i++ ){
    			Coord coord = coords.get(i);
    			options.add(new LatLng(coord.getX(),coord.getY()));
    	}

    	options.color(Color.BLUE);
    	options.width(5); 

    	gMap.addPolyline(options);
   
   	}

	private void setMarker(String message, double lat, double lng){

		//if(lastMarker != null)
        //      lastMarker.remove();

		MarkerOptions options = new MarkerOptions()
                                .title(message)
                                .position(new LatLng(lat,lng))
                          //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_location_found));
								.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        lastMarker = gMap.addMarker(options);

	}
	private void userMarker(String message, double lat, double lng){

		//if(lastMark removeMarker?
		MarkerOptions options = new MarkerOptions()
                                .title(message)
                                .position(new LatLng(lat,lng))
                          	.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_person));
		//color marker
					//		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        userMarker = gMap.addMarker(options);

	}
	


}