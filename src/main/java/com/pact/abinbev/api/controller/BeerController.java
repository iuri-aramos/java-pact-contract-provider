package com.pact.abinbev.api.controller;

import com.pact.abinbev.api.payloads.BeerRequest;
import com.pact.abinbev.api.payloads.BeerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pact.abinbev.service.BeerService;

@RestController
@RequestMapping("/beer")
public class BeerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeerController.class);

	private final BeerService beerService;

	@Autowired
	public BeerController(final BeerService beerService) {
		this.beerService = beerService;
	}

	@PostMapping(
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> postBeer(
					@RequestBody final BeerRequest beerRequest) {

		String idBeer = beerService.saveBeer(beerRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(new BeerResponse(idBeer));

	}

	@GetMapping(path = "/{idBeer}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> findBeerByIdBeer(@PathVariable("idBeer") final String idBeer) {

			return ResponseEntity.ok().body(beerService.getBeersByIdBeer(idBeer));
	}
}
