package com.alberto.springboot.searchatm.apirest.entity;

public class FeaturesATM {
	private String city;
	private String zipCode;
	private String longitude;
	private String latitude;
	
	public FeaturesATM(String city, String zipCode, String longitude, String latitude) {
		this.city = city;
		this.zipCode = zipCode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
