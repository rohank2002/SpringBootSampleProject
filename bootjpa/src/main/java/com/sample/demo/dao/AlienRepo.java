package com.sample.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sample.demo.model.Alien;

public interface AlienRepo extends JpaRepository<Alien, Integer> {
 
}
