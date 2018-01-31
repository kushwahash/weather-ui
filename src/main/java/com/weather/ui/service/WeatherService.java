package com.weather.ui.service;


import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.weather.ui.bean.WeatherReport;
import com.weather.ui.properties.WeatherServiceProperties;




@Component
public class WeatherService {

	private final RestOperations rest;
	private final String serviceUrl;
	private final String serviceUrlKey;
	public WeatherService(final RestTemplateBuilder builder, final WeatherServiceProperties props) {
		this.rest = builder.setReadTimeout(props.getReadTimeOut())
		.setConnectTimeout(props.getConnectTimeOut())
		.build();
		this.serviceUrl = props.getApiUrl();
		this.serviceUrlKey = props.getApiKey();
	}
	
	public WeatherReport getWeatherByCity(String city) {
		WeatherReport report  = new WeatherReport();
		String reportResult = rest.getForObject(serviceUrl,String.class,city,serviceUrlKey);
		try {
			JSONObject  reportObject = new JSONObject(reportResult);
			report = getReport(report,reportObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
//{"country":"US","description":"clear sky","minTemperature":"1.0","maxTemperature":"7.0","currentTemperature":"4.07","humidity":"41"}
	private WeatherReport getReport(WeatherReport report, JSONObject reportObject) {		
		try {
			report.setCurrentTemperature(reportObject.getString("currentTemperature"));
			report.setHumidity(reportObject.getString("humidity"));
			report.setMinTemperature(reportObject.getString("minTemperature"));
			report.setMaxTemperature(reportObject.getString("maxTemperature"));
			report.setCountry(reportObject.getString("country"));
			report.setDescription(reportObject.getString("description"));
			report.setCity(reportObject.getString("city"));
			report.setSunRise(reportObject.getString("sunRise"));
			report.setSunSet(reportObject.getString("sunSet"));
			report.setIcon(reportObject.getString("icon"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}

	private String convertToCelsius(String string) {
		// TODO Auto-generated method stub
		DecimalFormat df2 = new DecimalFormat(".##");
		return df2.format((Double.parseDouble(string)-273.15));
	}
}
