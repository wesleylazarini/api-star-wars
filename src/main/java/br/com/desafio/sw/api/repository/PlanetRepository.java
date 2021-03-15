package br.com.desafio.sw.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.sw.api.entity.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String>{

	Optional<Planet> findByName(String namePlanet);

}
