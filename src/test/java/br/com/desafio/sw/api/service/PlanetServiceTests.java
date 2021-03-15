package br.com.desafio.sw.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import br.com.desafio.sw.api.build.PlanetBuild;
import br.com.desafio.sw.api.entity.Planet;
import br.com.desafio.sw.api.repository.PlanetRepository;

@SpringBootTest
@Tag("UnitTest")
@TestPropertySource({ "/application-teste.properties" })
public class PlanetServiceTests {
	
	@Mock
	private PlanetRepository repo;
	
	@InjectMocks
	private PlanetService service = new PlanetService();
	
	@BeforeEach
	void setMockOutput() {
		when(repo.findByName(Mockito.any(String.class))).thenReturn(Optional.of(PlanetBuild.createPlanet()));
		when(repo.findById(Mockito.any(String.class))).thenReturn(Optional.of(PlanetBuild.createPlanet()));
//		doNothing().when(repo).delete(Mockito.any(Planet.class));

	}

	@Test
    void savePlanet() {
		
		when(repo.save(Mockito.any(Planet.class))).thenReturn(Mockito.any(Planet.class));
		
		Planet planet = PlanetBuild.createPlanet();
		
		service.save(planet);
		
		verify(repo, times(1)).save(Mockito.any(Planet.class));
    }
	
	
	@Test
	void findPlanets() {
		
		Page<Planet> pagedPlanets = new PageImpl<Planet>(PlanetBuild.listPlanets());
		
		when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(pagedPlanets);
		
		Page<Planet> planets = service.findAll(PageRequest.of(0, 20));

		verify(repo, times(1)).findAll(Mockito.any(Pageable.class));
		assertNotNull(planets);
		assertEquals(2, planets.getSize());
	}
	
	@Test
	void findByName() {
		
		String namePlanet = "Dagobah";
		
		Optional<Planet> planet = this.service.findByName(namePlanet);
		
		verify(repo, times(1)).findByName(Mockito.any(String.class));
		assertEquals(true, planet.isPresent());
		assertEquals("Dagobah", planet.get().getName());
		
	}
	
	@Test
	void findById() {
		
		String idPlanet = "604e387935d3ab2bf6a866c5";
		
		Optional<Planet> planet = this.service.findById(idPlanet);
		
		verify(repo, times(1)).findById(Mockito.any(String.class));
		assertEquals(true, planet.isPresent());
		assertEquals("604e387935d3ab2bf6a866c5", planet.get().getId());
		
	}
	
	@Test
    void removePlanet() {
		
		Planet planet = PlanetBuild.createPlanet();
		
		service.delete(planet);
		
		verify(repo, times(1)).delete(Mockito.any(Planet.class));
    }
}
