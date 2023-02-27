package com.pact.abinbev.data.repository;


import com.pact.abinbev.data.domain.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends MongoRepository<Beer, String> {

}
