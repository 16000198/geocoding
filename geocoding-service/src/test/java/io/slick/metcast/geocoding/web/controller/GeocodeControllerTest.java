package io.slick.metcast.geocoding.web.controller;

import io.slick.metcast.geocoding.domain.Location;
import io.slick.metcast.geocoding.service.GeocodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = GeocodeController.class)
class GeocodeControllerTest {
	@MockBean private GeocodeService mockGeocodeService;
	@Autowired private MockMvc mockMvc;

	@Test
	void convertAddressToLocationTest() throws Exception {
		when(mockGeocodeService.convertAddressToLocation(anyString()))
			.thenReturn(Optional.of(new Location("1", "2")));

		mockMvc.perform(
			get("/fake-address")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
			status().isOk()
		).andExpect(
			jsonPath("$.latitude", is("1"))
		).andExpect(
			jsonPath("$.longitude", is("2"))
		);

		verify(mockGeocodeService).convertAddressToLocation("fake-address");
		verifyNoMoreInteractions(mockGeocodeService);
	}
}
