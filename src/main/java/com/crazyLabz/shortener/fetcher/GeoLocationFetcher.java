package com.crazyLabz.shortener.fetcher;

import com.crazyLabz.shortener.entities.GeoLocation;

public interface GeoLocationFetcher {

    GeoLocation fetchLocation(String clientIp);
}
