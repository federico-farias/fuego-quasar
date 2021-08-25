package com.starwars.quasar.http.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteHttpRequest {

	private String name;

	private Double distance;

	private String[] message;

}
