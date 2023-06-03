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

import com.example.demo.entities.Specialite;
import com.example.demo.repositories.SpecialiteRepository;

@RestController
@RequestMapping("api/specialite")
public class SpecialiteController {
	@Autowired
	private SpecialiteRepository specialiteRepository;

	@GetMapping("find/{id}")
	public Specialite findById(@PathVariable(required = true) String id) {
		return specialiteRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("all")
	public List<Specialite> findAll() {
		return specialiteRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Specialite specialite) {
		specialiteRepository.save(specialite);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Specialite specialite = specialiteRepository.findById(Integer.parseInt(id));
		specialiteRepository.delete(specialite);
	}

	@GetMapping("count")
	public long count() {
		return specialiteRepository.count();
	}
}
