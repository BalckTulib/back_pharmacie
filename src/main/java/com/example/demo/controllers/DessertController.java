package com.example.demo.controllers;

import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Dessert;
import com.example.demo.repositories.DessertRepository;

@RestController
@RequestMapping("api/dessert")
public class DessertController {
	@Autowired
	private DessertRepository dessertRepository;

	@GetMapping("find/{id}")
	public Dessert findById(@PathVariable(required = true) String id) {
		return dessertRepository.findById(Integer.parseInt(id));
	}
	
	@GetMapping("all")
	public List<Dessert> findAll(){
		return dessertRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Dessert dessert) {
		dessertRepository.save(dessert);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Dessert dessert = dessertRepository.findById(Integer.parseInt(id));
		dessertRepository.delete(dessert);
	}
	
	@GetMapping("count")
	public long count() {
		return dessertRepository.count();
	}
}
