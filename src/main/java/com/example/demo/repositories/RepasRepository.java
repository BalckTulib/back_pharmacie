package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Repas;

@Repository
public interface RepasRepository extends JpaRepository<Repas, Integer> {
	Repas findById(int id);
}
