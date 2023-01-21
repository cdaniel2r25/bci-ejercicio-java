package com.example.bci.demoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bci.demoApi.models.Phones;

public interface PhonesRepository extends JpaRepository<Phones, String> {

}
