package com.starwars.quasar.http.schema;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSecretHttpRequest {
	
	private List<SatelliteHttpRequest> satellites;

}
