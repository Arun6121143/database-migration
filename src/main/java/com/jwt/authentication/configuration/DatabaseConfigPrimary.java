package com.jwt.authentication.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef ="primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionalManager",
        basePackages = {"com.jwt.authentication.primary"})
public class DatabaseConfigPrimary {

    @Value("${spring.primary.datasource.url}")
    private String url;

    @Value("${spring.primary.datasource.username}")
    private String username;

    @Value("${spring.primary.datasource.password}")
    private String password;

    @Primary
    @Bean(name = "primaryDbDataSource")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().url(url).username(username).password(password).build();
    }
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate otherJdbcTemplate(@Qualifier("primaryDbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    primaryEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("primaryDbDataSource")
    DataSource primaryDataSource){
        return builder.dataSource(primaryDataSource).packages("com.jwt.authentication")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionalManager")
    public PlatformTransactionManager primaryTransactionalManager(@Qualifier("primaryEntityManagerFactory")
                                                                    EntityManagerFactory primaryEntityManagerFactory){
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}