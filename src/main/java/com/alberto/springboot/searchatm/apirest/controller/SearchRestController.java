package com.alberto.springboot.searchatm.apirest.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.springboot.searchatm.apirest.entity.ATM;
import com.alberto.springboot.searchatm.apirest.entity.FeaturesATM;
import com.alberto.springboot.searchatm.apirest.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchRestController {
	@Autowired
	private SearchService searchservice;
	
	@GetMapping("/atm")
	public List<ATM> searchATM(@RequestBody FeaturesATM request){
		System.out.println(request.getCity()+" "+request.getZipCode()+" "+request.getLatitude()+" "+request.getLongitude()+" ");
		List<ATM> response = new LinkedList<ATM>();
		if(!request.getCity().isEmpty())
			response = searchservice.findByCity(request.getCity());
		else if(!request.getZipCode().isEmpty()) 
			response = searchservice.findByZipCode(Integer.parseInt(request.getZipCode()));
		else if(!request.getLatitude().isEmpty() && !request.getLongitude().isEmpty())
			response = searchservice.findByGPS(Float.parseFloat(request.getLatitude()), Float.parseFloat(request.getLongitude()));
		return response;
	}
}
