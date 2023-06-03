package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Specialite;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Integer>{
	Specialite findById(int id);
	Specialite findByNom(String nom);
}
