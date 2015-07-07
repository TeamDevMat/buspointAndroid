package com.project.buspoint.activities;

import com.project.buspoint.maps.MapApi;
import com.project.buspoint.location.LocationApi;
import com.project.buspoint.core.*;
import  com.project.buspoint.R;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;


import android.content.Intent;
import android.widget.Toast;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;



public class BusMapActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

	private LocationApi myLocation;
    private MapApi myMapApi;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        // comenzamos leindo una ruta por default.
        // depues una vez utilizada la app comenzamos con la ultima guardada en preferences
        // o despues de venir de una busqueda.

        myMapApi = new MapApi(this);
        myLocation = new LocationApi(this, myMapApi);

        Bundle bundle = getIntent().getExtras();
        
        if(bundle != null){

            String filename = bundle.getString("filename");
            String color = bundle.getString("color");
            
            Toast.makeText(this,filename + " " + color, Toast.LENGTH_SHORT).show();

            LectorCoord lectorCoord = new LectorCoord(this);
            List<Coord> coords = lectorCoord.all(filename);

            myMapApi.pintarRuta(coords);

         // myMapApi.pintaLugares(id_ruta);
         // float rgb[] = RGB.fromColorToInt("blanco");
       
        }
    }

    @Override
    public void onResume(){
        super.onResume();
      
    }
    @Override
    public void onStart(){
        super.onStart();
            myLocation.connect();
    }
 
    @Override
    public void onStop(){
        super.onStop();
        if(myLocation.isConnected())
            myLocation.disconnect();
    }
    @Override
    public void onPause(){
        super.onPause();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        
        // Configure the search info and add any event listeners
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.action_settings: 
                openSettings();
                break;
            default:
                break;    

        }
        
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        /*
        if (TextUtils.isEmpty(newText)) {
            //mListView.clearTextFilter();
        } else {
           // mListView.setFilterText(newText.toString());
        }
        */
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       
        Intent intent = new Intent(BusMapActivity.this, SearchableActivity.class);
        intent.setAction(Intent.ACTION_SEARCH);
   
        intent.putExtra("query", query);

        startActivity(intent);

        return true;
        
    }

    private void openSettings(){
        Intent intent = new Intent(BusMapActivity.this, AppPreferencesActivity.class);
        startActivity(intent);
    }
    

}
