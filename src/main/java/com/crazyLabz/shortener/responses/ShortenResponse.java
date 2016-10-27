package com.crazyLabz.shortener.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ShortenResponse {

    private String shortUrl;
}
