package br.com.desafio.sw.api.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.desafio.sw.api.entity.dto.PlanetApiSWDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "planets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {

	@Id
	private String id;

	@NotBlank
	private String name;

	@NotBlank
	private String climate;

	@NotBlank
	private String terrain;

	private Integer countFilms;

	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

	public Planet(PlanetApiSWDto planet) {
		this.name = planet.getName();
		this.climate = planet.getClimate();
		this.terrain = planet.getTerrain();
		this.countFilms = planet.getQtdFilms();
	}

}
