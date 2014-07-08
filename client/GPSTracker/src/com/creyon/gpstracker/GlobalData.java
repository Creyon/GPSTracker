package com.creyon.gpstracker; 

import android.app.Application;
import android.content.Intent;

public class GlobalData  {

	
	/** the server address **/
	public static final String URL_SERVER = "http://82.198.32.220:1337/user";
	
	/** The time lapse to request the location of the mobile and to send it back to the server**/
	public static int LOCATION_INTERVAL = 10000;

	private static GlobalData instance;

	public static GlobalData getInstance(){
		return (instance == null) ? new GlobalData() : instance;
	}
	
	
}
