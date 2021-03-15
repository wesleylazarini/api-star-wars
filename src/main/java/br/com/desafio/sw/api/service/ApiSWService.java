package br.com.desafio.sw.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafio.sw.api.entity.Planet;
import br.com.desafio.sw.api.entity.dto.PlanetApiSWDto;

@Service
public class ApiSWService {
	
	@Autowired
	private ObjectMapper objectMapper;
	
//	@Cacheable("apiSW")
	public Planet getListPlanetApiSW(String namePlanet) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("https://swapi.dev/api/planets/?search=".concat(namePlanet.trim()), HttpMethod.GET, null, String.class);
		
		try {
			JsonNode json = objectMapper.readTree(responseEntity.getBody());
			List<PlanetApiSWDto> planets = objectMapper.readValue(json.get("results").toString(), new TypeReference<List<PlanetApiSWDto>>() {});
			if(planets !=  null && !planets.isEmpty())
				return new Planet(planets.get(0));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
