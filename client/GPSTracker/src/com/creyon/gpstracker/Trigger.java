package com.creyon.gpstracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Trigger extends BroadcastReceiver{

	/** 
	 *  Whenever the user boots his device this method is called 
	 * **/
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
	   
		context.startService(
				new Intent(context,Tracker.class)
		);
	} 
}
