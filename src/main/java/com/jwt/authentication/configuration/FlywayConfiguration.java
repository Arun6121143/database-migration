package com.jwt.authentication.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {
    public void Migration(DataSource dataSource,String locations,String commonLocations){
        Flyway.configure().dataSource(dataSource).
                locations(locations,commonLocations).
                outOfOrder(true).
                baselineOnMigrate(true).
                load().
                migrate();
    }
}
