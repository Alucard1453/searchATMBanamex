package com.alberto.springboot.searchatm.apirest.service;

import java.util.List;

import com.alberto.springboot.searchatm.apirest.entity.ATM;

public interface SearchService {
	public List<ATM> findByZipCode(int zipcode);
	public List<ATM> findByCity(String city);
	public List<ATM> findByGPS(float latitude, float longitude);
}
