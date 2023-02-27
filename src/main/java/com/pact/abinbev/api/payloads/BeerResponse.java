package com.pact.abinbev.api.payloads;

import lombok.Data;

@Data
public class BeerResponse {

	private String id;

	public BeerResponse(final String id) {
		this.id = id;
	}
}
