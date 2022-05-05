package com.projects.rest.api.scraping.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.rest.api.scraping.models.Product;
import com.projects.rest.api.scraping.service.IScraperService;

@RestController
@RequestMapping(path = "/v1/ps5")
public class PsFiveController {
	
	@Autowired
	private IScraperService scraperService;
	
	@GetMapping(path = "/all")
	public Set<Product> getOfferPlayStationFive(){
		
		return scraperService.getAllPlayStationFive();
		
	}

}
