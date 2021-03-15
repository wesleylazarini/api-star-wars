package br.com.desafio.sw.api.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetApiSWDto {
	
    private String id;
	
	private String name;
	
	private String climate;
	
	private String terrain;
	
	private List<String> films;
	
	public Integer getQtdFilms() {
		if(this.films == null || this.films.isEmpty())
			return 0;
		
		return this.films.size();
	}

}
