package io.slick.metcast.geocoding.web.controller;

import io.slick.metcast.geocoding.api.Location;
import io.slick.metcast.geocoding.integration.GeocodeClient;
import io.slick.metcast.geocoding.integration.NominatimObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GeocodeControllerE2ETest {
	@Autowired private TestRestTemplate testRestTemplate;
	@MockBean private GeocodeClient geocodeClient;

	@Test
	void convertAddressToLocation() {
		when(geocodeClient.locationByAddress(anyString(), anyString(), anyString()))
			.thenReturn(
				ResponseEntity.ok(
					List.of(new NominatimObject("59.123", "37.961", "Abc"))
				)
			);

		ResponseEntity<Location> actualResponse = testRestTemplate.getForEntity("/abc street", Location.class);

		verify(geocodeClient).locationByAddress("abc street", "json", "1");
		verifyNoMoreInteractions(geocodeClient);

		Location location = actualResponse.getBody();
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
		assertNotNull(location);
		assertEquals("59.123", location.getLatitude());
		assertEquals("37.961", location.getLongitude());
	}
}
