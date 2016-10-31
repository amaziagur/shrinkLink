package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.GeoLocation;
import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.fetcher.GeoLocationFetcher;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PersistedRedirectService implements RedirectService{

    private UrlAssetRepository assetRepository;
    private GeoLocationFetcher  geoLocationFetcher;

    @Autowired
    public PersistedRedirectService(UrlAssetRepository assetRepository, GeoLocationFetcher  geoLocationFetcher) {
        this.assetRepository = assetRepository;
        this.geoLocationFetcher = geoLocationFetcher;
    }

    @Override
    public String redirect(String id, String clientIp) {
        UrlAsset urlAsset = getUrlAsset(id);
        gatherStats(clientIp, urlAsset);
        return urlAsset.getFullUrl();
    }

    private void gatherStats(String clientIp, UrlAsset urlAsset) {
        GeoLocation visitorLocation = geoLocationFetcher.fetchLocation(clientIp);
        UrlAsset asset = UrlAsset.updateHits(urlAsset, clientIp, visitorLocation);
        assetRepository.save(asset);
    }

    private UrlAsset getUrlAsset(String id) {
        return (UrlAsset) Optional.ofNullable(assetRepository.findOne(id))
                    .orElseThrow(() -> new AssetNotFoundException("asset: " + id + " not found!"));
    }
}
