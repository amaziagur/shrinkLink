package com.crazyLabz.shortener.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class IconAsset {
    private String url;
    private String width;
    private String height;
    private String format;
    private String bytes;
    private String error;
    private String sha1sum;
}
