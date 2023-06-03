package com.example.demo.repositories;

import java.time.LocalDate;	
import java.util.List;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Repas;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Ville;
import com.example.demo.entities.Zone;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	Restaurant findById(int id);
}
