package com.excercise.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameIntegrationTest {


	@LocalServerPort
	private int serverPort;

	private String GAME_FIZZBUZZ_URI = null;


	@BeforeEach
	public void setUp() {
		GAME_FIZZBUZZ_URI = "http://localhost:" + serverPort + "/game/fizzbuzz/generate";
	}

	@Test
	public void getFizzBuzzList_WithNoMaxParam_ReturnsDefaultValues() throws Exception {

		ResponseEntity<List<String>> response = restTemplate().exchange(GAME_FIZZBUZZ_URI, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() {
				});

		assertEquals(response.getBody().size(), 100);
		assertThat(response.getBody()).contains("Fizz", "Buzz", "FizzBuzz");
	}

	@Test
	public void getFizzBuzzList_WithMax_ReturnsLimited()  throws Exception {

		URI uri = UriComponentsBuilder.fromUriString(GAME_FIZZBUZZ_URI)
				.queryParam("maxValue", "5")
				.build()
				.toUri();
		ResponseEntity<List<String>> response = restTemplate().exchange(uri, HttpMethod.GET,
				null,new ParameterizedTypeReference<List<String>>() {});

		assertEquals(5,response.getBody().size());
		assertThat(response.getBody()).isEqualTo(Arrays.asList("1", "2", "Fizz", "4", "Buzz"));

	}

	@Test
	public void getFizzBuzzList_WithMax_IncludesAllValues()  throws Exception {

		URI uri = UriComponentsBuilder.fromUriString(GAME_FIZZBUZZ_URI)
				.queryParam("maxValue", "15")
				.build()
				.toUri();
		ResponseEntity<List<String>> response = restTemplate().exchange(uri, HttpMethod.GET,
				null,new ParameterizedTypeReference<List<String>>() {});

		assertEquals(15,response.getBody().size());
		assertThat(response.getBody()).isEqualTo(Arrays.asList("1",
				"2",
				"Fizz",
				"4",
				"Buzz",
				"Fizz",
				"7",
				"8",
				"Fizz",
				"Buzz",
				"11",
				"Fizz",
				"Fizz",
				"14",
				"FizzBuzz"));

	}

	@Test
	public void getFizzBuzzList_WithInvalidMax_ReturnsEmpty()  throws Exception {

		URI uri = UriComponentsBuilder.fromUriString(GAME_FIZZBUZZ_URI)
				.queryParam("maxValue", "-5")
				.build()
				.toUri();
		ResponseEntity<List<String>> response = restTemplate().exchange(uri, HttpMethod.GET,
				null,new ParameterizedTypeReference<List<String>>() {});

		assertEquals(0,response.getBody().size());
		assertThat(response.getBody()).isEmpty();

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
}
