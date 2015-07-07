package com.project.buspoint.database;

import java.io.IOException;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;



public class SQLiteManager {

	private SQLiteDatabase sqliteDB;
	private SQLiteHelper sqliteHelper;

	public SQLiteManager(Context context){
		sqliteDB = null;
		sqliteHelper = new SQLiteHelper(context);
	}

	public void open(){
		try{
			
			sqliteHelper.createDatabase();
			sqliteHelper.openDataBase();
			sqliteDB = sqliteHelper.getReadableDatabase();

		}catch(IOException io){
			throw new Error("Unable to create database");	
		}catch(SQLException sql){
			throw sql;
		}
	}
	
	public void close(){
	
		sqliteHelper.close();
	}

	public Cursor selectLike(String tableName, String[] returnColumns, 
							 String sortOrder, String columnName, 
							 String like)
	{

		Cursor cursor = sqliteDB.query(
			tableName,
			returnColumns,
			columnName + " LIKE '?'",
			new String[]{like + "%"},
			null,
			null,
			sortOrder	
		);

		return cursor;
	}

	public Cursor select(String tableName,String[] projection,String sortOrder)
	{

		Cursor cursor = sqliteDB.query(
			tableName,  // The table to query
			projection,                               // The columns to return
			null,                                // The columns for the WHERE clause
			null,                            // The values for the WHERE clause
			null,                                     // don't group the rows
			null,                                     // don't filter by row groups
			sortOrder                                 // The sort order
			);
			
		return cursor;
	}

}