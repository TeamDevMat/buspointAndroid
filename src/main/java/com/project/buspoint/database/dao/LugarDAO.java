package com.project.buspoint.database.dao;

import android.database.Cursor;

public interface LugarDAO {

	//el cursor sera cambiado por el modelo de la app
	public Cursor selectAll();
	public Cursor select(String query);
	public Cursor selectWithNameLike(String like);
	public boolean exists(int id);
	public boolean exists(String key);
	
}