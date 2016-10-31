package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.GeoLocation;
import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.fetcher.GeoLocationFetcher;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class PersistedRedirectServiceTest {

    public static final String CLIENT_IP = "127.0.0.1";
    private static final String URL_ID = "urlId";
    private static final String FULL_URL = "http://www.crazyLabz.com";
    private static final Map<String , GeoLocation> VISITOR = new HashMap<>();
    private UrlAsset URL_ASSET;

    private static final UrlAsset URL_ASSET_REVISIT = UrlAsset.builder()
            .fullUrl(FULL_URL)
            .shortUrl(URL_ID)
            .visitors(VISITOR)
            .hits(1)
            .build();

    private UrlAssetRepository assetRepository = mock(UrlAssetRepository.class);
    private GeoLocationFetcher locationFetcher = mock(GeoLocationFetcher.class);
    PersistedRedirectService redirectService = new PersistedRedirectService(assetRepository, locationFetcher);

    @Before
    public void init(){
        URL_ASSET = UrlAsset.builder()
                .fullUrl(FULL_URL)
                .shortUrl(URL_ID)
                .hits(0)
                .build();
    }

    @Test
    public void shouldLocatefullUrlGivenId(){
        when(assetRepository.findOne(URL_ID)).thenReturn(URL_ASSET);
        String redirect = redirectService.redirect(URL_ID, CLIENT_IP);
        assertTrue(redirect.equals(URL_ASSET.getFullUrl()));
    }

    @Test(expected = RedirectService.AssetNotFoundException.class)
    public void shouldThrowNotFoundException(){
        when(assetRepository.findOne(URL_ID)).thenReturn(null);
        redirectService.redirect(URL_ID, CLIENT_IP);
    }

    @Test
    public void shouldCountNewVisitorHits(){
        updateNumberOfVisits(1);
        when(assetRepository.findOne(URL_ID)).thenReturn(URL_ASSET);
        when(locationFetcher.fetchLocation(CLIENT_IP)).thenReturn(new GeoLocation());
        redirectService.redirect(URL_ID, CLIENT_IP);
        verify(assetRepository).save(bumpHitCountersOnAsset(1));
    }

    @Test
    public void shouldCountReVisitHits(){
        updateNumberOfVisits(2);
        when(assetRepository.findOne(URL_ID)).thenReturn(URL_ASSET_REVISIT);
        when(locationFetcher.fetchLocation(CLIENT_IP)).thenReturn(new GeoLocation());
        redirectService.redirect(URL_ID, CLIENT_IP);
        verify(assetRepository).save(bumpHitCountersOnAsset(2));
    }

    private UrlAsset bumpHitCountersOnAsset(long totalHits) {
        return UrlAsset.builder()
                .fullUrl(FULL_URL)
                .shortUrl(URL_ID)
                .visitors(VISITOR)
                .hits(totalHits)
                .build();
    }

    private void updateNumberOfVisits(int hits){
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setHits(hits);
        VISITOR.put(CLIENT_IP, geoLocation);
    }
}