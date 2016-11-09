package com.crazyLabz.shortener.fetcher;

import com.crazyLabz.shortener.entities.GeoLocation;
import com.crazyLabz.shortener.entities.IconsAsset;
import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IconFetcherService implements IconFetcher{

    private RestTemplate restTemplate = new RestTemplate();
    private static final String DOMAIN = "http://icons.better-idea.org/allicons.json?pretty=true&url=   ";

    @Override
    public String fetchIconFor(String fullUrl) {
        ResponseEntity<String> response = restTemplate.exchange(DOMAIN + fullUrl, HttpMethod.GET, null, String.class);
        IconsAsset iconsAsset = new Gson().fromJson(response.getBody(), IconsAsset.class);
        return grabIcon(iconsAsset);
    }

    private String grabIcon(IconsAsset iconsAsset) {
        return iconsAsset.getIcons().stream().filter(i -> Integer.valueOf(i.getWidth()) < 100).findFirst().get().getUrl();
    }

}
