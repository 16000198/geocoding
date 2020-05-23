package io.slick.metcast.geocoding.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;


@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Location {
	@NonNull private final String latitude;
	@NonNull private final String longitude;
}
