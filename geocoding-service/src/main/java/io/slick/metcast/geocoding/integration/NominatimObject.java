package io.slick.metcast.geocoding.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class NominatimObject {
	@JsonProperty("lat") private final String latitude;
	@JsonProperty("lon") private final String longitude;
	@JsonProperty("display_name") private final String displayName;
}
