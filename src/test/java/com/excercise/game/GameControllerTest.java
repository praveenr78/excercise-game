package com.excercise.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    private static final String GAME_FIZZBUZZ_URI = "/game/fizzbuzz/generate";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FizzBuzzGenerator fizzBuzzGenerator;

    @Test
    public void whenGetWithNoMaxParam_Returns200_DefaultsValues() throws Exception {

        given(fizzBuzzGenerator.generate(null))
                .willReturn(Arrays.asList("1", "Fizz"));

        final MvcResult actualResult = mvc.perform(get(GAME_FIZZBUZZ_URI)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();


        assertThat(actualResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(
                        Arrays.asList("1", "Fizz")));
    }

    @Test
    public void whenGetWithMaxParam_Returns200_LimitsToMax() throws Exception {
        given(fizzBuzzGenerator.generate(anyInt()))
                .willReturn(Arrays.asList("1", "Fizz", "Buzz"));

        final MvcResult actualResult = mvc.perform(get(GAME_FIZZBUZZ_URI)
                .param("maxValue", "3")
                .contentType("application/json"))

                .andExpect(status().isOk())
                .andReturn();

        assertThat(actualResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(
                        Arrays.asList("1", "Fizz", "Buzz")));
    }

    @Test
    public void whenGetWithParam_Returns200_VerifyAllOptions() throws Exception {

        List<String> fixtures = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
                "Fizz", "Buzz", "11", "Fizz", "Fizz", "14", "FizzBuzz");

        given(fizzBuzzGenerator.generate(15))
                .willReturn(fixtures);

        final MvcResult actualResult = mvc.perform(get(GAME_FIZZBUZZ_URI)
                .param("maxValue", "15")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(actualResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(
                        fixtures));
    }

    @Test
    public void whenGetWithInvalidParam_Returns_NoValues() throws Exception {

        List<String> fixtures = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz");

        given(fizzBuzzGenerator.generate(15))
                .willReturn(fixtures);

        final MvcResult actualResult = mvc.perform(get(GAME_FIZZBUZZ_URI)
                .param("maxValue", "-1")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(actualResult.getResponse().getContentAsString()).isEqualTo("[]");
    }

    @Test
    public void whenGetWithMalformedURL_Returns404() throws Exception {


        final MvcResult actualResult = mvc.perform(get("/game/fizzbuzz")
                .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andReturn();


        assertThat(actualResult.getResponse().getContentAsString()).isEmpty();
    }
}
