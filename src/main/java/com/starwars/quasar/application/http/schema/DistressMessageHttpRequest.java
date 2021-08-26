package com.starwars.quasar.application.http.schema;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistressMessageHttpRequest {
	
	private List<SatelliteHttpRequest> satellites;

}
