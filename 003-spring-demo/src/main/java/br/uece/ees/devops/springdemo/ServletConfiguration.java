package br.uece.ees.devops.springdemo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class ServletConfiguration {
    @Bean @ApplicationScope
    public ServletRegistrationBean<HelloWorldServlet> customServletBean() {
        return new ServletRegistrationBean<>(new HelloWorldServlet(), "/servlet");
    }
}
