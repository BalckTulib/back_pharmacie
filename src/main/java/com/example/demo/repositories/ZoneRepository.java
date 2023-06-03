package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
	Zone findById(int id);
}
