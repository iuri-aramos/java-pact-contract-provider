package com.pact.abinbev.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BeerRequest {

	private String name;
	private String brand;
	private BigDecimal price;
	private String type;

}
