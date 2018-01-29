package com.weather.ui.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weather.ui.bean.City;


@RestController
public class WeatherController {
	@RequestMapping(value = "/defaultCityTest", method = RequestMethod.GET,
			produces = { "application/json", "application/xml" })
	public City defaultCity() {
		City city = new City();
		city.setCountry("USA");
		city.setName("Plano");	
		return city;
		
	}
	
	@RequestMapping(value = "/getCityList", method = RequestMethod.GET,
			produces = { "application/json", "application/xml" })
	public City[] getCityList() {
		City[] cityList = readJSONFile();
		return cityList;
		
	}
	
	public City[] readJSONFile(){
		 BufferedReader reader = null;
		    try {
		        reader = new BufferedReader(new FileReader("city.list.json"));
		        Gson gson = new GsonBuilder().create();
		        City[] cityList = gson.fromJson(reader, City[].class);
		        return cityList;
		    } catch (FileNotFoundException ex) {
		        ex.printStackTrace();
		        return null;
		    } 
		    
	}
}
