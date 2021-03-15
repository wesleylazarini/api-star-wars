package br.com.desafio.sw.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.desafio.sw.api.entity.Planet;
import br.com.desafio.sw.api.repository.PlanetRepository;

@Service
public class PlanetService implements PlanetServiceImpl {

	@Autowired
	private PlanetRepository repository;

	@Override
	public void save(Planet planet) {
		repository.save(planet);
	}

	@Override
	public Page<Planet> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Optional<Planet> findByName(String namePlanet) {
		return repository.findByName(namePlanet);
	}

	@Override
	public Optional<Planet> findById(String idPlanet) {
		return repository.findById(idPlanet);
	}
	
	@Override
	public void delete(Planet planet) {
		repository.delete(planet);
	}

}
