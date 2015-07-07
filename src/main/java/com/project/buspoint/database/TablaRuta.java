package com.project.buspoint.database;

import android.util.Log;
import android.provider.BaseColumns;

public class TablaRuta{
	public static final String TAG = "TablaRuta";
	public static final String NOMBRE = "ruta";
	
	public static final String DELETE_ENTRIES = 
		SQLSentence.DROP_TABLE + SQLSentence.IF_EXISTS + NOMBRE;
	
	public static final String CREATE_ENTRIES = 
		SQLSentence.CREATE_TABLE + NOMBRE +
		SQLSentence.OPEN_PARAMS+
			Columna._ID + SQLSentence.INTEGER_PRIMARY_KEY + SQLSentence.COMMA +
			Columna.NOMBRE + SQLSentence.TEXT_TYPE + SQLSentence.COMMA +
			Columna.NUMERO + SQLSentence.INT_TYPE + SQLSentence.COMMA +
			Columna.COLOR + SQLSentence.INT_TYPE + SQLSentence.COMMA +
		SQLSentence.CLOSE_PARAMS;

		public static abstract class Columna implements BaseColumns{

			
			public static final String NOMBRE = "nombre";
			public static final String NUMERO = "numero";
			public static final String COLOR = "color";

		}	

		public void test(){
			Log.i(TAG, CREATE_ENTRIES);
		}
}