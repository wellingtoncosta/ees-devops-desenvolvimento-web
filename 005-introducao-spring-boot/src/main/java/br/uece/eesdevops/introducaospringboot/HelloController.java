package br.uece.eesdevops.introducaospringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greetings() {
        return "Hello world!";
    }

}
