package br.uece.eesdevops.introducaospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IntroducaoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroducaoSpringBootApplication.class, args);
    }

}
