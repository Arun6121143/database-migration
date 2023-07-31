package com.jwt.authentication.configuration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class PrimaryDataBaseMigration {

    @Autowired
    private FlywayConfiguration flywayConfiguration;
    @Autowired
    @Qualifier("primaryDbDataSource")
    private DataSource primaryDataSource;

    @Value("${spring.flyway.primary.locations}")
    private String locations;

    @Value("${spring.flyway.locations}")
    private String commonLocations;

    @PostConstruct
    public void primaryMigration(){
       flywayConfiguration.Migration(primaryDataSource,locations,commonLocations);
    }
}