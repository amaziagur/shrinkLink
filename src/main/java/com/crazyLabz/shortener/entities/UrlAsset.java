package com.crazyLabz.shortener.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Builder
@Document(collection = "urls")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UrlAsset {

    @Id
    private String id;

    @Indexed
    private String shortUrl;

    @Indexed
    private String fullUrl;

    private long hits;

    private  Map<String, GeoLocation> visitors;

    // https://reinhard.codes/2016/07/13/using-lomboks-builder-annotation-with-default-values/
    public static class UrlAssetBuilder{
        private Map<String, GeoLocation> visitors = new HashMap<>();
    }

    public static UrlAsset updateHits(UrlAsset urlAsset, String visitor, GeoLocation visitorLocation){
        return UrlAsset.builder()
                .hits(urlAsset.getHits() + 1)
                .id(urlAsset.id)
                .fullUrl(urlAsset.fullUrl)
                .shortUrl(urlAsset.shortUrl)
                .visitors(updateVisitorStats(urlAsset, visitor, visitorLocation))
                .build();
    }

    private static Map<String, GeoLocation> updateVisitorStats(UrlAsset urlAsset, String visitor, GeoLocation visitorLocation){
        if(urlAsset.visitors.get(visitor) != null) {
            GeoLocation.bumpHits(visitorLocation);
        }

        urlAsset.visitors.put(visitor, visitorLocation);

        return urlAsset.visitors;
    }

    /*private static Map<String, Long> calcVisitorHits(Map<String, Long> currentVisitors, String visitor){
        Optional<Long> hits = Optional.ofNullable(currentVisitors.get(visitor));
        currentVisitors.put(visitor, hits.isPresent() ? hits.get() + 1: 1L);
        return currentVisitors;
    }*/
}
