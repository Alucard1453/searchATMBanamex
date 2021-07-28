package com.alberto.springboot.searchatm.apirest.service;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsSame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alberto.springboot.searchatm.apirest.entity.ATM;

@Service
public class JsonParsingService implements ParsingService{
	@Autowired
	private RestTemplate restTemplate;
	private List<ATM> atmList = new LinkedList<ATM>();
	
	public List<ATM> parse(String url) {
		restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String json = restTemplate.getForObject(url, String.class);
		json=json.substring(13, json.length()-2);
		
		JSONParser parser = new JSONParser();
		JSONObject response;
		try {
			response = (JSONObject) parser.parse(json);
			response = (JSONObject) response.get("Servicios");
			
			Collection<JSONArray> respuesta = new LinkedList<JSONArray>();
			respuesta= response.values();

			respuesta.stream().forEach((Object jsonObject)->{
				Collection<JSONArray> childSuperior = new LinkedList<JSONArray>();
				childSuperior =  ((HashMap) jsonObject).values();
				childSuperior.stream().forEach((Object o)->{
					Collection<JSONArray> childs = new LinkedList<JSONArray>();
					childs = ((HashMap) o).values();
					childs.stream().forEach((Object p)->{
						Object[] array = ((ArrayList) p).toArray();
						String[] info = array[4].toString().split(", ");
						if(info.length>3 ) {
							ATM atm = new ATM();
							atm.setCity(info[1]);
							atm.setStreet(array[3].toString().substring(0,array[3].toString().length()-1));
							atm.setSuburb(info[0]);
							atm.setZipCode(info[2].substring(4).trim());
							atm.setState(array[17].toString());
							atm.setReferences(array[6].toString());
							atm.setLatitude(array[16].toString());
							atm.setLongitude(array[15].toString());
							atm.setSchedule(array[13].toString()+" - "+array[14].toString());
							atm.setType(array[19].toString());
							atmList.add(atm);						
						}
					});
				});
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return atmList;
	}
}
