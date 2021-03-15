package br.com.desafio.sw.api.build;

import java.util.Arrays;
import java.util.List;


import br.com.desafio.sw.api.entity.Planet;

public class PlanetBuild {

	public static Planet createPlanet() {
		return new Planet("604e387935d3ab2bf6a866c5", "Dagobah", "murky", "swamp, jungles", 3);
	}

	public static List<Planet> listPlanets() {
		return Arrays.asList(new Planet("604e387935d3ab2bf6a866c5", "Bespin", "temperate", "gas giant", 3), new Planet("604e387935d3ab2bf6a866c5", "Dagobah", "murky", "swamp, jungles", 3));
	}

	public static Planet createPlanet_Integration() {
		
		return new Planet("Endor", "temperate", "forests, mountains, lakes");
	}
	
	public static List<Planet> createListPlanet_Integration() {
		
		return Arrays.asList(new Planet("Bespin", "temperate", "gas giant"), 
					new Planet("Kashyyyk", "tropical", "jungle, forests, lakes, rivers"), 
					new Planet("Dagobah", "murky", "swamp, jungles"),
					new Planet("Polis Massa", "artificial temperate", "airless asteroid"),
					new Planet("Trandosha", "arid", "mountains, seas, grasslands, deserts"),
					new Planet("Socorro", "arid", "deserts, mountains"),
					new Planet("Ord Mantell", "temperate", "plains, seas, mesas"));
		
	}

}
