package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Dessert;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Integer>{
	Dessert findById(int id);
}
