package io.slick.metcast.geocoding.service;

import io.slick.metcast.geocoding.domain.Location;
import io.slick.metcast.geocoding.integration.GeocodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@Service
@RequiredArgsConstructor
public class GeocodeService {
	private final GeocodeClient geocodeClient;

	public Optional<Location> convertAddressToLocation(String address) {
		return Optional.ofNullable(geocodeClient.locationByAddress(address, "json", "1").getBody())
			.filter(Predicate.not(List::isEmpty))
			.map(list -> list.get(0))
			.map(o -> new Location(o.getLatitude(), o.getLongitude()));
	}
}
