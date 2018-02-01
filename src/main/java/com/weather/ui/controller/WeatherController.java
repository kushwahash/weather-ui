package com.weather.ui.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weather.ui.bean.City;
import com.weather.ui.bean.USCity;
import com.weather.ui.bean.WeatherReport;
import com.weather.ui.service.WeatherService;
import com.weather.ui.util.JsonUtil;


@RestController
public class WeatherController {
	
	@Autowired
	WeatherService service;
	
	
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
		City[] cityList = JsonUtil.readWorlCityJSONFile();
		return cityList;
		
	}
	
	@RequestMapping(value = "/getUSCityList", method = RequestMethod.GET,
			produces = { "application/json", "application/xml" })
	public USCity[] getUSCityList() {
		USCity[] USCityList = JsonUtil.readUSJSONFile();
		return USCityList;
		
	}
	
	@RequestMapping(value = "/getWeatherByCity", method = RequestMethod.GET,
			produces = { "application/json", "application/xml" })
	public WeatherReport getWeatherByCity(@RequestParam("city") String city) {
		WeatherReport report = new WeatherReport();
		report = service.getWeatherByCity(city);
		return report;
		
	}
	
	@RequestMapping(value = "/getDefaultWeatherByCity", method = RequestMethod.GET,
			produces = { "application/json", "application/xml" })
	public WeatherReport getDefaultWeatherByCity(@RequestParam("city") String city) {
		WeatherReport report = new WeatherReport();
		//report = service.getWeatherByCity(city);
		report.setCountry("USA");
		report.setCurrentTemperature("20");
		report.setDescription("Rain");
		report.setMaxTemperature("21");
		report.setMinTemperature("12");
		report.setHumidity("25");
		report.setIcon("10d");
		report.setCity("Plano");
		report.setDescription("Rain in View");
		return report;
		
	}
	
	
}
