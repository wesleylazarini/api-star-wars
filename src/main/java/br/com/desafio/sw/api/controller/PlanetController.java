package br.com.desafio.sw.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafio.sw.api.entity.Planet;
import br.com.desafio.sw.api.service.ApiSWService;
import br.com.desafio.sw.api.service.PlanetService;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

	@Autowired
	private PlanetService service;
	
	@Autowired
	private ApiSWService swService;

	@PostMapping
	public ResponseEntity<?> savePlanet(@Valid @RequestBody Planet planet) {
		
		Planet resp = swService.getListPlanetApiSW(planet.getName());
		
		if(resp == null)
			return ResponseEntity.badRequest().body("Planeta não faz parte do universo Star Warss");
		
		if(this.service.findByName(planet.getName()).isPresent())
			return ResponseEntity.badRequest().body("Planeta já cadastrado");
		
		this.service.save(planet);
		
		planet.setCountFilms(resp.getCountFilms());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri();

		return ResponseEntity.created(uri).body(planet);

	}

	@GetMapping(value = "/list")
	public ResponseEntity<?> listPlanets(Pageable pageable) {

		Page<Planet> planets = this.service.findAll(pageable);
		
		
		planets.forEach(p -> {
			Planet resp = swService.getListPlanetApiSW(p.getName());
			if(resp != null)
				p.setCountFilms(resp.getCountFilms());
		});
		
		

		return ResponseEntity.ok(planets);
	}

	@GetMapping
	public ResponseEntity<?> findByName(@RequestParam("name") String namePlanet) {

		Optional<Planet> planet = this.service.findByName(namePlanet);

		if (!planet.isPresent())
			return ResponseEntity.notFound().build();
		
		Planet resp = swService.getListPlanetApiSW(planet.get().getName());
		
		if(resp != null)
			planet.get().setCountFilms(resp.getCountFilms());

		return ResponseEntity.ok(planet.get());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String idPlanet) {

		Optional<Planet> planet = this.service.findById(idPlanet);

		if (!planet.isPresent())
			return ResponseEntity.notFound().build();
		
		Planet resp = swService.getListPlanetApiSW(planet.get().getName());
		
		if(resp != null)
			planet.get().setCountFilms(resp.getCountFilms());

		return ResponseEntity.ok(planet.get());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> removePlanet(@PathVariable("id") String idPlanet) {

		Optional<Planet> planet = this.service.findById(idPlanet);

		if (!planet.isPresent())
			return ResponseEntity.notFound().build();
		
		this.service.delete(planet.get());

		return ResponseEntity.noContent().build();
	}

}
