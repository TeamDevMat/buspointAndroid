package com.project.buspoint.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.database.Cursor;
import android.widget.AdapterView;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.database.SQLException;
import android.widget.Toast;
import android.database.sqlite.SQLiteCursor;
import android.content.Intent;

import com.project.buspoint.R;
import com.project.buspoint.database.SQLiteHelper;
import com.project.buspoint.database.TablaRuta;

public class RutaListActivity extends Activity  implements OnItemClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.obtenerruta);


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
            null,                                // The columns for the WHERE clause
            null,                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
        );

        String from[] = new String[] {TablaRuta.Columna.NOMBRE, TablaRuta.Columna.NUMERO};
        int to[] = new int[]{ R.id.item_nombre, R.id.item_numero};

        SimpleCursorAdapter cAdapter = 
                    new SimpleCursorAdapter(this, R.layout.ruta_item,cursor,from,to,0);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(cAdapter);

        listView.setOnItemClickListener(this);


	}

	
	@Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id){

    	SQLiteCursor sqliteCursor = (SQLiteCursor) adapter.getItemAtPosition(position);
    	//Couldn't read row 0, col 3 from CursorWindow.  
    	//Make sure the Cursor is initialized correctly before accessing data from it.

    	final String filename = sqliteCursor.getString(2);//obtener el string de este registro en la columna [2]
    	final String color = sqliteCursor.getString(3);


    	//aqui llamo a la otra activity y paso los parametros.
    	
	Intent intent = new Intent(RutaListActivity.this, BusMapActivity.class);
    	intent.putExtra("filename",filename);
    	intent.putExtra("color",color);
    	startActivity(intent);

    }
        

}
