package br.com.desafio.sw.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.desafio.sw.api.entity.Planet;

public interface PlanetServiceImpl {

	void save(Planet planet);
	
	Page<Planet> findAll(Pageable pageable);

	Optional<Planet> findByName(String namePlanet);

	Optional<Planet> findById(String idPlanet);

	void delete(Planet planet);
	
}
