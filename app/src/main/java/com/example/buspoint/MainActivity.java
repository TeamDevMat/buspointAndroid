package com.example.buspoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {

    String[] opciones={"Ruta 1 Sección 16/Puerta México","Ruta 2 Esperanza/Colonia Tecnológico","Ruta 3 Popular/Puerta México","Ruta 4 Mariano/Puerta México"};
	
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	        
        spinner1 = (Spinner) findViewById(R.id.spinner1);
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void operar(View view) {
        String selec=spinner1.getSelectedItem().toString();
            Intent i = new Intent(this, ObtenerRuta.class );
            i.putExtra("param1", selec);
            startActivity(i);
     }
    
}