package com.example.bci.demoApi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {

  private static final Logger log = LogManager.getLogger(HealthCheckController.class);

  @GetMapping(value = "/healthcheck")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> healthcheck() {
    log.info("Llamada a healthcheck");
    return ResponseEntity.ok("healthcheck ok");
  }

}
