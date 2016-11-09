package com.crazyLabz.shortener.entities;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class IconsAsset {
    private String url;
    private List<IconAsset> icons;
}
