package com.example.buspoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

	private Spinner spinner1;
	
	public static Map<Integer,String> RUTAS = createMap();
	public String[] options = {"Seccion 16/Puerta Mexico",
        						"Esperanza/Colonia Tecnologico",
        						"Popular/Puerta Mexico",
        						"Mariano/Puerta Mexico"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	        
        spinner1 = (Spinner) findViewById(R.id.spinner1);
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        									this,
        									android.R.layout.simple_spinner_item, 
        									options);
        spinner1.setAdapter(adapter); 
	
	}


	public void operar(View view) {

        String select=spinner1.getSelectedItem().toString();
        	
        Intent i = new Intent(this, ObtenerRuta.class);
        startActivity(i);
    }

    // Estos datos son una consulta al servidor.
     private static Map<Integer, String> createMap() {

        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(1, "Seccion 16/Puerta Mexico");
        result.put(2, "Esperanza/Colonia Tecnologico");
        result.put(3, "Popular/Puerta Mexico");
        result.put(4, "Mariano/Puerta Mexico");
        

        return Collections.unmodifiableMap(result);
    }
}


//La primera selecione es una consulta a la base de datos.
//Osea que las rutas las proporciona el servidor.

//La tabla peseras contiene un identificador ruta.

/*Ese identificador lo usaremos para hacer la consulta de 
 *las peseras que esten en la ruta que selecciono el usuario.
 */