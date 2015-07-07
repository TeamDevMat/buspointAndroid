package com.project.buspoint.dabase.dao;

import android.database.Cursor;
import android.content.Context;

import com.project.buspoint.database.SQLiteManager;
import com.project.buspoint.database.dao.LugarDAO;
import com.project.buspoint.database.TablaLugar;

public class SQLiteLugarDAO implements LugarDAO{

	private static final String TABLE_NAME = TablaLugar.NOMBRE; 
	private static final String[] COLUMNS_TO_RETURN = {
													 TablaLugar.Columna._ID,	
													 TablaLugar.Columna.NOMBRE,
													 TablaLugar.Columna.CATEGORIA,
													 TablaLugar.Columna.LATITUD,
													 TablaLugar.Columna.LONGITUD,
													 TablaLugar.Columna.CP,
													 TablaLugar.Columna.COLONIA,
													 TablaLugar.Columna.CALLE
													 };
    
	private static final String SORT_ORDER = TablaLugar.Columna._ID + " ";											 
	private static final String LIKE_COLUMN = TablaLugar.Columna.NOMBRE;


	SQLiteManager manager;

	public SQLiteLugarDAO(Context context){
		
		manager = new SQLiteManager(context);
		
	}
	//el cursor sera cambiado por el modelo de la app
	public Cursor selectAll(){
		
		
			manager.open();
		//select(String tableName,String[] projection,String sortOrder):Cursor
			Cursor cursor = manager.select(TABLE_NAME, COLUMNS_TO_RETURN, SORT_ORDER);
			manager.close();
			
			return cursor;

	}
	public Cursor select(String query){
		return null;
	}
	public Cursor selectWithNameLike(String like){

		manager.open();
		/*	public Cursor selectLike(String tableName, String[] returnColumns, 
							 String sortOrder, String columnName, 
							 String like)*/
		Cursor cursor = manager.selectLike(TABLE_NAME, COLUMNS_TO_RETURN,
										   SORT_ORDER,LIKE_COLUMN, like);
		manager.close();

		return cursor;

	}
	public boolean exists(int id){
		return false;
	}
	public boolean exists(String key){
		return false;
	}
	
}

