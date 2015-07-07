package com.project.buspoint.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import java.io.IOException;
import android.database.sqlite.SQLiteCursor;
import android.database.Cursor;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.AdapterView;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.database.SQLException;

import com.project.buspoint.R;
import com.project.buspoint.database.SQLiteHelper;
import com.project.buspoint.database.TablaRuta;

public class SearchableActivity extends ActionBarActivity implements OnItemClickListener{

	public static String JARGON; 

	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.search);
		
        handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
    	setIntent(intent);
    	handleIntent(intent);
	}

	private void handleIntent(Intent intent){

		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      		
      		String query = intent.getStringExtra("query");
      		Toast.makeText(this,query, Toast.LENGTH_SHORT).show();
      		doMySearch(query);
    	}
	}
	private void doMySearch(String query){
		//AQUI VOY HACER LA CONSULTA A LA BASE DE DATOS Y MOSTAR LOS RESULTADOS EN UN LISTS VIEW.
		SQLiteHelper helper = new SQLiteHelper(this);
		SQLiteDatabase sqlite = null;

		try{
            helper.createDatabase();
            helper.openDataBase();
            //helper.close();
            sqlite = helper.getReadableDatabase();

        }catch(IOException io){
            throw new Error("Unable to create database");
        }catch(SQLException esql){
            throw esql;
        }

        String[] projection = {
            TablaRuta.Columna._ID,
            TablaRuta.Columna.NOMBRE,
            TablaRuta.Columna.NUMERO,
            TablaRuta.Columna.COLOR
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =  TablaRuta.Columna._ID + "";

        Cursor cursor = sqlite.query(
            TablaRuta.NOMBRE,  // The table to query
            projection,                               // The columns to return
            TablaRuta.Columna.NOMBRE + " LIKE ?",     // The columns for the WHERE clause
            new String[]{"%"+ query +"%"},            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
        );

        String from[] = new String[] {
                            TablaRuta.Columna.NOMBRE, 
                            TablaRuta.Columna.NUMERO, 
                            TablaRuta.Columna.COLOR
                        };

        int to[] = new int[]{ R.id.item_nombre, R.id.item_numero};
        
        if(cursor != null){
            SimpleCursorAdapter cAdapter = 
                    new SimpleCursorAdapter(this, R.layout.ruta_item,cursor,from,to,0);

            ListView listView = (ListView) findViewById(R.id.list);
            if(listView != null){
                listView.setAdapter(cAdapter);

             listView.setOnItemClickListener(this);
            }
        }


	}

	@Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id){

    	SQLiteCursor sqliteCursor = (SQLiteCursor) adapter.getItemAtPosition(position);
    	//Couldn't read row 0, col 3 from CursorWindow.  
    	//Make sure the Cursor is initialized correctly before accessing data from it.

    	final String filename = sqliteCursor.getString(2);//obtener el string de este registro en la columna [2]
    	final String color = sqliteCursor.getString(3);


    	//aqui llamo a la otra activity y paso los parametros.
    	
	Intent intent = new Intent(SearchableActivity.this, BusMapActivity.class);
    intent.putExtra("filename",filename);
    intent.putExtra("color",color);
    startActivity(intent);

    }
}

