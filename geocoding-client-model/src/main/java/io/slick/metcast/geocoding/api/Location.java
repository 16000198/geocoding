package io.slick.metcast.geocoding.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class Location {
	@JsonProperty("latitude") private final String latitude;
	@JsonProperty("longitude") private final String longitude;
}
