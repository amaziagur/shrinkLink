package com.crazyLabz.shortener.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public static UrlAsset updateHits(UrlAsset urlAsset){
        return UrlAsset.builder()
                .hits(urlAsset.getHits() + 1)
                .id(urlAsset.id)
                .fullUrl(urlAsset.fullUrl)
                .shortUrl(urlAsset.shortUrl)
                .build();
    }
}
