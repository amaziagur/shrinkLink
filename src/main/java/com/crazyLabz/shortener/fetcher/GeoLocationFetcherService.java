package com.crazyLabz.shortener.fetcher;

import com.crazyLabz.shortener.entities.GeoLocation;
import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationFetcherService implements GeoLocationFetcher{

    private RestTemplate restTemplate = new RestTemplate();

    private static final String DOMAIN = "http://freegeoip.net/json/";

    @Override
    public GeoLocation fetchLocation(String clientIp) {
        ResponseEntity<String> response = restTemplate.exchange(DOMAIN + "141.226.162.117", HttpMethod.GET, null, String.class);
        return new Gson().fromJson(response.getBody(), GeoLocation.class);
    }
}
