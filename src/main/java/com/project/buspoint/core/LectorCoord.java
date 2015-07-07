package com.project.buspoint.core;

import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import android.content.Context;
import java.io.InputStream;
import android.content.res.AssetManager;

public class LectorCoord implements OnReadCoord{

	List<Coord> coords;
	String filename;
	Context context;

	public LectorCoord(Context c){
			
			context = c;
			coords = new ArrayList<Coord>();
	}

	@Override
	public List<Coord> all(){
		return coords;
	}

	@Override
	public List<Coord> all(String queryFile){
	 
		String file = queryFile + ".txt";
	 
		AssetManager asset =  context.getResources().getAssets();
    	InputStream inputStream = null;
		
		//Abrir un archivo de la carpeta.
		try{
            inputStream = asset.open(file);

            if(inputStream != null){
            	BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                //Leer el archivo completo      
                while(br.ready()){
                        //almacenar una linea por ejemplo "23.2323 43.23343"
                        String s = br.readLine();
                        
                        //Separa la liena "23.3434 43.3434" en "23.3434" y "43.3434"
                        String[] coord = s.split(",");

                        //convertir los strings a Double
                        double x = Double.parseDouble(coord[0]);
                        double y = Double.parseDouble(coord[1]);


                        //Crear un objeto del tipo Coord - crear una instancia de la clase Coord
                        Coord tempCoord = new Coord(x, y);

                        //Agregarlos a la lista 
                        coords.add(tempCoord);
                }

            }

        }catch(IOException e){
              e.printStackTrace();
        }    
     	
		//leer los archivos.
		return coords;
	}

	 private String toString(InputStream in, String encode)  throws IOException{
        //string buffer
        StringBuffer content =  new StringBuffer();
                
        //reader
        Reader reader = new BufferedReader(new InputStreamReader(in,encode));
                        
                        //char[]
                        char[] buffer =new char[1024];
                        int n;

                        while((n = reader.read(buffer)) != -1){
                                content.append(buffer,0,n);
                        }

                return content.toString();
                        
        }

}