package br.uece.eesdevops.introducaospringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public BeanClass beanClass() {
        return new BeanClass();
    }

    @Bean
    public BeanClassConsumer beanClassConsumer() {
        return new BeanClassConsumer(beanClass());
    }

    public static class BeanClass {

        public void print() {
            System.out.println("Instance = " + this.hashCode());
        }

    }

    public static class BeanClassConsumer {

        public BeanClassConsumer(BeanClass beanClass) {
            beanClass.print();
        }

    }

}
