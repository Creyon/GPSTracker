package com.creyon.gpstracker; 

import java.util.HashMap;


/**
 * 
 * Container of the info that will be send to the server. 
 *  
 *  
 * **/

public class Message {

	private Float lat; 
	private Float lng; 
	private String sim;
	
	public HashMap<String,String> toPackage(){
		HashMap<String,String> pack = new HashMap();

		pack.put("lat", Float.toString(this.getLat()));
		pack.put("lng", Float.toString(this.getLng()) );
		pack.put("sim", this.getSim());
			
		return pack;
	}

	public Float getLat() {
		return lat;
	}
	public Message setLat(Float lat) {
		this.lat = lat;
		return this;
	}
	public Float getLng() {
		return lng;
	}
	public Message setLng(Float lng) {
		this.lng = lng;
		return this;
	}
	public String getSim() {
		return sim;
	}
	public Message setSim(String sim) {
		this.sim = sim;
		return this;
	} 
	
	
	
}
