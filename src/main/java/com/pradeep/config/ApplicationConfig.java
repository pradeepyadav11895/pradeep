package com.pradeep.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.pradeep.backend.persistence.repositories")
@EntityScan(basePackages = "com.pradeep.backend.persistence.domain.backend" )
@EnableTransactionManagement
@PropertySource("file:///${user.home}/.pradeep/application-common.properties")
public class ApplicationConfig {
}
