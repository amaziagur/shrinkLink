package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.fetcher.IconFetcher;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class PersistedShortenerService implements ShortenerService {

    @Setter
    @Value("${default.suffix.length:8}")
    private int suffixLength;

    @Setter
    @Value("${default.domain}")
    private String defaultDomain;

    UrlAssetRepository assetRepository;

    IconFetcher iconFetcher;

    @Autowired
    public PersistedShortenerService(UrlAssetRepository assetRepository, IconFetcher iconFetcher){
        this.assetRepository = assetRepository;
        this.iconFetcher = iconFetcher;
    }

    @Override
    public String shorten(String url, String prefix, int length) {
        String shortened = generateShort(length);
        String icon = iconFetcher.fetchIconFor(url);
        String siteName = grabSiteName(url);
        logUrlMetaData(url, prefix, shortened, icon, siteName);
        return calcPrefix(prefix) + shortened;
    }

    private String grabSiteName(String url) {
        URL myUrl = null;
        try {
            myUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return myUrl.getHost();
    }

    private String calcPrefix(String prefix) {
        if(prefix == null){
            prefix = defaultDomain;
        }
        return prefix;
    }

    private void logUrlMetaData(String url, String prefix, String id, String icon, String siteName) {
        assetRepository.save(UrlAsset.builder().fullUrl(url).id(id).shortUrl(calcPrefix(prefix) + id).icon(icon).siteName(siteName).build());
    }

    private String generateShort(int length) {
        return RandomStringUtils.randomAlphanumeric(calcSuffixLength(length));
    }

    private int calcSuffixLength(int length) {
        return length == 0 ? suffixLength : length;
    }
}
