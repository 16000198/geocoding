package io.slick.metcast.geocoding.web.controller;

import io.slick.metcast.geocoding.api.Location;
import io.slick.metcast.geocoding.service.GeocodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class GeocodeController {
	private final GeocodeService geocodeService;

	@GetMapping("/{address}")
	public ResponseEntity<Location> convertAddressToLocation(@PathVariable String address) {
		return ResponseEntity.of(
			geocodeService.convertAddressToLocation(address)
				.map(l -> new Location(l.getLatitude(), l.getLongitude()))
		);
	}
}
