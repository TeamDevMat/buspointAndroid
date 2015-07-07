package com.project.buspoint.location;

import android.location.Location;


public interface DirtyLocationListener{

	public void onDirtyLocationChanged(Location location);
	public void onDirtyConnected(Location location);
}