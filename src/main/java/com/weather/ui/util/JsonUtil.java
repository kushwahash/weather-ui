package com.weather.ui.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weather.ui.bean.City;
import com.weather.ui.bean.USCity;

public class JsonUtil {
	
	public static City[] readWorlCityJSONFile(){
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
	
	public static USCity[] readUSJSONFile(){
		 BufferedReader reader = null;
		    try {
		        reader = new BufferedReader(new FileReader("US_Cities_Zip.json"));
		        Gson gson = new GsonBuilder().create();
		        USCity[] UScityList = gson.fromJson(reader, USCity[].class);
		        return UScityList;
		    } catch (FileNotFoundException ex) {
		        ex.printStackTrace();
		        return null;
		    } 
		    
	}

}
