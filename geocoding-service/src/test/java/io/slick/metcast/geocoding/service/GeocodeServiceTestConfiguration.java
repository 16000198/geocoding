package io.slick.metcast.geocoding.service;

import io.slick.metcast.geocoding.integration.GeocodeClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class GeocodeServiceTestConfiguration {
	@Bean
	public GeocodeService geocodeService(GeocodeClient geocodeClient) {
		return new GeocodeService(geocodeClient);
	}
}
