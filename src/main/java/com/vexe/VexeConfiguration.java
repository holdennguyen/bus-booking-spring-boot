package com.vexe;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.vexe")
@EntityScan(basePackages = "com.vexe.model")
@EnableJpaRepositories(basePackages = "com.vexe.repository")
public class VexeConfiguration {
    // Configuration beans will be added here as needed
} 