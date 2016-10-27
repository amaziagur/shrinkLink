package com.crazyLabz.shortener.requests;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ShortenRequest {
    private String url;

    @NotNull
    private String prefix;

    private int suffixLength;
}
