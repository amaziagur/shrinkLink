package com.crazyLabz.shortener.fetcher;

import com.crazyLabz.shortener.entities.GeoLocation;
import com.google.gson.Gson;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationFetcherService implements GeoLocationFetcher{

    @Setter
    @Value("${geo.token}")
    private String token;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String DOMAIN = "http://api.ipstack.com/";

    @Override
    public GeoLocation fetchLocation(String clientIp) {
        ResponseEntity<String> response = restTemplate.exchange(DOMAIN + clientIp + "?" + token, HttpMethod.GET, null, String.class);
        return new Gson().fromJson(response.getBody(), GeoLocation.class);
    }
}
