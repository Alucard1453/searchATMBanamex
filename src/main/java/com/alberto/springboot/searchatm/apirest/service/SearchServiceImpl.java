package com.alberto.springboot.searchatm.apirest.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alberto.springboot.searchatm.apirest.entity.ATM;


@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
    private ParsingService parsingService;
	private static final String url = "https://www.banamex.com/localizador/jsonP/json5.json";
	private List<ATM> listAtms = new LinkedList<ATM>();
	private List<ATM> response = new LinkedList<ATM>();

	public List<ATM> findByZipCode(int zipcode) {
		response.clear();
		listAtms = parsingService.parse(url);
		listAtms.stream().forEach((ATM atm)->{
			if(!isRegisterExists(atm) && atm.isNearbyZP(Integer.toString(zipcode)) && !response.contains(atm))
				response.add(atm);
		});
		return response;
	}

	public List<ATM> findByCity(String city) {
		response.clear();
		listAtms = parsingService.parse(url);
		listAtms.stream().forEach((ATM atm)->{
			if(!isRegisterExists(atm) && atm.isSameCity(city) && !response.contains(atm))
				response.add(atm);
		});
		return response;
	}

	public List<ATM> findByGPS(float latitude, float longitude) {
		response.clear();
		listAtms = parsingService.parse(url);
		listAtms.stream().forEach((ATM atm)->{
			if(!isRegisterExists(atm) && atm.isNearByGPS(latitude,longitude) && !response.contains(atm))
				response.add(atm);
		});
		return response;
	}
	
	public boolean isRegisterExists(ATM atm) {
		boolean bool = false;
		for (ATM atmTmp : response) {
			if(atmTmp.getLatitude().equals(atm.getLatitude()) && atmTmp.getLongitude().equals(atm.getLongitude()) && atmTmp.getType().equals(atm.getType()) ) {
				bool = true;
			}
		}
		return bool;
	}
}
