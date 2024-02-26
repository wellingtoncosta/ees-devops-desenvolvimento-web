package br.uece.ees.devops.springdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HelloWorldController {
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Greetings greetings() {
        return new Greetings("Hello world!");
    }
}
