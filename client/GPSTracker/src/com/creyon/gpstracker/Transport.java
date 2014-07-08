package com.creyon.gpstracker;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Transport {

	private static  Transport instance = null;

	private static RequestQueue reQ;

	 public Transport(Context context) {
		reQ = (this.reQ == null) ? Volley.newRequestQueue(context) : this.reQ;
	}

	/**
	 *  Asynchronous call to the server  
	 * 
	 * **/
	public Transport sendMessage(final Message msg){
		Log.e("tracker","intentando enviar data : "+ msg.toPackage().toString());
		StringRequest postReq = new StringRequest(Request.Method.POST, GlobalData.URL_SERVER , new Response.Listener<String>() {
		    @Override
		    public void onResponse(String response) {
		    	 Log.e("Transport","response successed : "+ response);
		    }
		}, new Response.ErrorListener() {
		    @Override
		    public void onErrorResponse(VolleyError error) {
		    	Log.e("Transport","response failed "+ error);
		        System.out.println("Error ["+error+"]");
		    }
		}){
			@Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				return  msg.toPackage();
			}
		};
		reQ.add(postReq);		
		return this;
	}

}
