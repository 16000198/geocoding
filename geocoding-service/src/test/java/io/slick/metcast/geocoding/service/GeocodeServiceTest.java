package io.slick.metcast.geocoding.service;

import io.slick.metcast.geocoding.domain.Location;
import io.slick.metcast.geocoding.integration.GeocodeClient;
import io.slick.metcast.geocoding.integration.NominatimObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@SpringJUnitConfig(classes = GeocodeServiceTestConfiguration.class)
class GeocodeServiceTest {
	@MockBean private GeocodeClient geocodeClient;
	@Autowired private GeocodeService geocodeService;

	@Test
	void validLocation() {
		when(geocodeClient.locationByAddress(anyString(), anyString(), anyString()))
			.thenReturn(
				ResponseEntity.ok(
					List.of(new NominatimObject("59.123", "37.961", "Abc"))
				)
			);

		var expectedLocation = new Location("59.123", "37.961");

		Location actualLocation = geocodeService.convertAddressToLocation("Abc").orElseThrow();

		assertEquals(expectedLocation, actualLocation);
		verify(geocodeClient).locationByAddress("Abc", "json", "1");
		verifyNoMoreInteractions(geocodeClient);
	}

	@Test
	void invalidLocation() {
		when(geocodeClient.locationByAddress(anyString(), anyString(), anyString()))
			.thenReturn(ResponseEntity.ok(List.of()));

		var address = ".";
		var expectedLocation = Optional.empty();

		Optional<Location> actualLocation = geocodeService.convertAddressToLocation(address);

		assertEquals(expectedLocation, actualLocation);
	}
}
