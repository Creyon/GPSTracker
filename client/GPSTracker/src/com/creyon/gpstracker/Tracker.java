package com.creyon.gpstracker;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.creyon.gpstracker.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Tracker extends IntentService implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener,
		com.google.android.gms.location.LocationListener {

	private LocationManager locationManager;
	private boolean isFineLocationEnable = false;
	private String provider;

	private Location location;
	private LocationClient locationClient;
	private LocationRequest locationRequest;

	public Tracker() {
		super("tracker service");
	}

	public Tracker(String name) {
		super("tracker service");
		// TODO Auto-generated constructor stub
	}

	// Called whenever the service is instantiated. Good for
	// initializing components
	@Override
	public void onCreate() {
		locationRequest = LocationRequest.create();
		locationRequest.setFastestInterval(GlobalData.LOCATION_INTERVAL).setPriority(
				LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

		locationClient = new LocationClient(this, this, this);
		if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS)
			locationClient.connect();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		Toast.makeText(this, "Desde el service arranqué. onstartcommand",
				Toast.LENGTH_SHORT).show();
		// setUpLocationHandler();
		return Service.START_REDELIVER_INTENT;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("Tracker", "Estoy destruyendo el service");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.e("Tracker", "Onhandleintent ha sido llamado");
	}

	/** if location is changed send the new location to the server **/
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		float lat = (float) (location.getLatitude());
		float lng = (float) (location.getLongitude());

		Log.e("Tracker", "Location changed lat:[" + Float.toString(lat)
				+ "] lng: [" + Float.toString(lng) + "]");

		Message msg = new Message().setLat(lat).setLng(lng)
				.setSim(this.getSim());

		new Transport(this).sendMessage(msg);
	}

	private String getSim() {
		// TODO Auto-generated method stub
		TelephonyManager tMgr = (TelephonyManager) getBaseContext()
				.getSystemService(getBaseContext().TELEPHONY_SERVICE);
		String mPhoneNumber = tMgr.getSimSerialNumber();

		return mPhoneNumber;
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Log.e("Tracker", "Configuration failed");
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Log.e("Tracker", "Connected");
		locationClient.requestLocationUpdates(locationRequest, this);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Log.e("Tracker", "Disconnected");
	}

}
