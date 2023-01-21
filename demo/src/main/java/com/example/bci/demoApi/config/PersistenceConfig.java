package com.example.bci.demoApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.bci.demoApi.config.auditor.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {


  @Bean
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }


}
