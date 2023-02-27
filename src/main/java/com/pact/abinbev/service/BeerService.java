package com.pact.abinbev.service;

import com.pact.abinbev.api.payloads.BeerRequest;
import com.pact.abinbev.data.domain.Beer;
import com.pact.abinbev.data.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeerService.class);

	private final BeerRepository beerRepository;

	@Autowired
	public BeerService(final BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}
	public String saveBeer(final BeerRequest beerRequest) {
		final Beer beer = new Beer();

		beer.setBrand(beerRequest.getBrand());
		beer.setName(beerRequest.getName());
		beer.setPrice(beerRequest.getPrice());
		beer.setType(beerRequest.getType());

		final Beer beerSaved = beerRepository.save(beer);
		LOGGER.info(beerSaved.toString());
		return beerSaved.getId();

	}

	public Beer getBeersByIdBeer(final String idBeer) {

		final Optional<Beer> byId = beerRepository.findById(idBeer);

		final Beer beer = byId.orElse(null);

		return beer;
	}
}
