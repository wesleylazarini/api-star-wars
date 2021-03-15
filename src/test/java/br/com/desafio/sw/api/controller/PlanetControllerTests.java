package br.com.desafio.sw.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafio.sw.api.build.PlanetBuild;
import br.com.desafio.sw.api.entity.Planet;
import br.com.desafio.sw.api.util.RestResponsePage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("IntegrationTest")
@TestPropertySource({ "/application-teste.properties" })
public class PlanetControllerTests {
	
	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	HttpHeaders headers;

	private static RestTemplate restTemplate = null;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired MongoTemplate mongo;
	
	@BeforeTestClass
	public void beforeClass() {
		mongo.insert(new Planet("Teste", "tropical", "normal"), "planets");
	}

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/planets");
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		mongo.dropCollection("planets");
	}
	
	@InjectMocks
	private PlanetController controller = new PlanetController();

//	@Test
//	@Order(1)
	void savePlanet() throws Exception {
		
		Planet planet = PlanetBuild.createPlanet_Integration();

		HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(planet), headers);

		ResponseEntity<Planet> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, Planet.class);

		Planet createdPlanet = responseEntity.getBody();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertNotNull(createdPlanet.getId());

	}

//	@Test
//	@Order(3)
	void findPlanets() {

		List<Planet> planets = PlanetBuild.createListPlanet_Integration();

		planets.forEach(p -> mongo.save(p));

		ResponseEntity<RestResponsePage<Planet>> responseEntity = restTemplate.exchange(baseUrl.concat("/list"), HttpMethod.GET, null,
				new ParameterizedTypeReference<RestResponsePage<Planet>>() {});

		List<Planet> listPlanets = responseEntity.getBody().getContent();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertFalse(listPlanets.isEmpty());

	}

//	@Test
//	@Order(2)
	void findByName() {

		Planet planet = PlanetBuild.createPlanet_Integration();

		mongo.save(planet);

		ResponseEntity<Planet> responseEntity = restTemplate.exchange(baseUrl + "?name=" + planet.getName(),
				HttpMethod.GET, null, Planet.class);

		Planet createdPlanet = responseEntity.getBody();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertNotNull(createdPlanet.getId());
		assertEquals(createdPlanet.getName(), planet.getName());
	}

//	@Test
//	@Order(4)
	void findById() {
		
		Planet planet = PlanetBuild.createPlanet_Integration();

		mongo.save(planet);

		ResponseEntity<Planet> responseEntity = restTemplate.exchange(baseUrl + "/" + planet.getId(),
				HttpMethod.GET, null, Planet.class);

		Planet createdPlanet = responseEntity.getBody();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertNotNull(createdPlanet.getId());
		assertEquals(createdPlanet.getId(), planet.getId());
	}
	
//	@Test
//	@Order(5)
	void removePlanet() {
		
		Planet planet = PlanetBuild.createPlanet_Integration();

		mongo.save(planet);

		ResponseEntity<Planet> responseEntity = restTemplate.exchange(baseUrl + "/" + planet.getId(),
				HttpMethod.DELETE, null, Planet.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
