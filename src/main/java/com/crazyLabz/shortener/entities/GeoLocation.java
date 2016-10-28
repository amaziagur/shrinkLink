package com.crazyLabz.shortener.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class GeoLocation {

    private String ip;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("country_name")
    private String countryName;
    @SerializedName("region_code")
    private String regionCode;
    @SerializedName("region_name")
    private String regionName;
    private String city;
    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("time_zone")
    private String timeZone;
    private double latitude;
    private double longitude;
    @SerializedName("metro_code")
    private int metroCode;
}
