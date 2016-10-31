package com.crazyLabz.shortener.entities;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UrlStatsResponse {
    private UrlAsset asset;
}
