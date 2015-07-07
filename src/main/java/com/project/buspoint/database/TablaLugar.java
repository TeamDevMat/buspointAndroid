package com.project.buspoint.database;

import android.util.Log;
import android.provider.BaseColumns;

public class TablaLugar{
	public static final String TAG = "TablaLugar";
	public static final String NOMBRE = "lugar";
	
	public static final String DELETE_ENTRIES = 
		SQLSentence.DROP_TABLE + SQLSentence.IF_EXISTS + NOMBRE;
		//Esta accion esta pendiente porquue nosotros no creamos la base solo leemos una ya exisitente
		/*public static final String CREATE_ENTRIES = 
		SQLSentence.CREATE_TABLE + NOMBRE +
		SQLSentence.OPEN_PARAMS+
			Columna._ID + SQLSentence.INTEGER_PRIMARY_KEY + SQLSentence.COMMA +
			Columna.NOMBRE + SQLSentence.TEXT_TYPE + SQLSentence.COMMA +
			Columna.NUMERO + SQLSentence.INT_TYPE + SQLSentence.COMMA +
			Columna.COLOR + SQLSentence.INT_TYPE + SQLSentence.COMMA +
		SQLSentence.CLOSE_PARAMS;*/

		public static abstract class Columna implements BaseColumns{

			
			public static final String NOMBRE = "nombre";
			public static final String CATEGORIA = "categoria";
			public static final String LATITUD = "latitud";
			public static final String LONGITUD = "longitud";
			public static final String CALLE = "calle";
			public static final String CP="cp";
			public static final String COLONIA ="colonia";
					
		}	

		public void test(){
			//Log.i(TAG, CREATE_ENTRIES);
		}
}