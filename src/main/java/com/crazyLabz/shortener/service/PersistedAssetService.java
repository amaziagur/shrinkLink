package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PersistedAssetService implements AssetsService {

    private UrlAssetRepository assetRepository;

    @Autowired
    public PersistedAssetService(UrlAssetRepository assetRepository){
        this.assetRepository = assetRepository;
    }

    @Override
    public List<UrlAsset> findAll() {
        return assetRepository.findAll();
    }
}
