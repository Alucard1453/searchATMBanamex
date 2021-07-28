package com.alberto.springboot.searchatm.apirest.service;

import java.util.List;

import com.alberto.springboot.searchatm.apirest.entity.ATM;

public interface ParsingService {
	List<ATM> parse(String url);
}
