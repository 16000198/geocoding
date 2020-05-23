package io.slick.metcast.geocoding.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "geocode-client", url = "${geocoder.api-url}")
public interface GeocodeClient {
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<NominatimObject>> locationByAddress(@RequestParam("q") String address,
	                                                        @RequestParam String format,
	                                                        @RequestParam String limit);
}
