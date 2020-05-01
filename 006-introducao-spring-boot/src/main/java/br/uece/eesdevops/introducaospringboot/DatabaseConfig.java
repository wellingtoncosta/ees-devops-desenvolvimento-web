package br.uece.eesdevops.introducaospringboot;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DatabaseConfig {

//    @Bean
//    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
//        HikariConfig config = new HikariConfig();
//
//        config.setDriverClassName(dataSourceProperties.getDriverClassName());
//        config.setJdbcUrl(dataSourceProperties.getUrl());
//        config.setUsername(dataSourceProperties.getUsername());
//        config.setPassword(dataSourceProperties.getPassword());
//
//        return new HikariDataSource(config);
//    }

}
