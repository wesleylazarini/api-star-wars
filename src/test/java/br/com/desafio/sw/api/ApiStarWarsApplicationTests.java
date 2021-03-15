package br.com.desafio.sw.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource({ "/application-teste.properties" })
class ApiStarWarsApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
