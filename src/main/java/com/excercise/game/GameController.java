package com.excercise.game;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/game")
@Validated
@Slf4j
public class GameController {

    private FizzBuzzGenerator fizzBuzzGenerator;

    /**
     * <p> Generate fizz buzz game</p>
     *
     * @param maxValue the max value to generate
     * @return
     */
    @GetMapping("/fizzbuzz/generate")
    @ResponseBody
    public ResponseEntity<List<String>> generate(
            @RequestParam(required = false) Integer maxValue) {
        return ResponseEntity.ok(fizzBuzzGenerator.generate(maxValue));
    }
}
