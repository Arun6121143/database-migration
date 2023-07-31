package com.jwt.authentication.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef ="secondaryEntityManagerFactory",
transactionManagerRef = "secondaryTransactionalManager",
basePackages = {"com.jwt.authentication.secondary"})
public class SecondaryDatabaseConfig {

    @Value("${spring.secondary.datasource.url}")
    private String url;

    @Value("${spring.secondary.datasource.username}")
    private String username;

    @Value("${spring.secondary.datasource.password}")
    private String password;

    @Bean(name = "secondaryDbDataSource")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().url(url).username(username).password(password).build();
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate otherJdbcTemplate(@Qualifier("secondaryDbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("secondaryDbDataSource")
    DataSource secondaryDataSource){
    return builder.dataSource(secondaryDataSource).packages("com.jwt.authentication.secondary")
            .build();
    }
    @Bean(name = "secondaryTransactionalManager")
    public PlatformTransactionManager secondaryTransactionalManager(@Qualifier("secondaryEntityManagerFactory")
                                                                    EntityManagerFactory secondaryEntityManagerFactory){
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}