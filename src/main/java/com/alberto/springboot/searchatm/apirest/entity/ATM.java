package com.alberto.springboot.searchatm.apirest.entity;

import java.text.Normalizer;

public class ATM {
	private String city;
	private String street;
	private String suburb;
	private String zipCode;
	private String state;
	private String references;
	private String latitude;
	private String longitude;
	private String schedule;
	private String type;
	
	public ATM() {
	}

	public ATM(String city, String street, String suburb, String zipCode, String state, String references,
			String latitude, String longitude, String schedule, String type) {
		this.city = city;
		this.street = street;
		this.suburb = suburb;
		this.zipCode = zipCode;
		this.state = state;
		this.references = references;
		this.latitude = latitude;
		this.longitude = longitude;
		this.schedule = schedule;
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSameCity(String city) {
		String original = Normalizer.normalize(this.city, Normalizer.Form.NFD);
		original = original.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		
		city = Normalizer.normalize(city, Normalizer.Form.NFD);
		city = city.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

		if(original.equalsIgnoreCase(city) || original.contains(city))
			return true;
		else
			return false;
	}
	
	public boolean isNearByGPS(float latitude, float longitude) {
		if( (Float.parseFloat(this.latitude) > (latitude - 0.03f) && Float.parseFloat(this.latitude) < (latitude + 0.03f)) &&  (Float.parseFloat(this.longitude) > (longitude - 0.03f) && Float.parseFloat(this.longitude) < (longitude + 0.03f))  ) 
			return true;
		else
			return false;
	}
	
	public boolean isNearbyZP(String zipcode) {
		System.out.println(this.zipCode.substring(0,3));
		if( this.zipCode.substring(0,3).equals(zipcode.substring(0,3)) ) 
			return true;
		else
			return false;
	}
}
