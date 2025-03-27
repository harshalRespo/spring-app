package com.myspring.journalApp.service;


import com.myspring.journalApp.api.response.WeatherResponse;
import com.myspring.journalApp.cache.AppCache;
import com.myspring.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    //@Value("${weather.api.key}")
    private final static String apikey = "798efd4d648f40ac4b36b0459a7c45d7";
    private final static String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
   try
   {
       String finalAPI =  appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apikey);
       System.out.println(finalAPI);
       ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
       WeatherResponse body = response.getBody();
       return body;
   }
   catch(Exception e)
   {
       System.out.println(e);
       return null;

   }

    }

}
