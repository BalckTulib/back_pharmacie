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

import com.example.demo.entities.Entree;
import com.example.demo.repositories.EntreeRepository;

@RestController
@RequestMapping("api/entree")
public class EntreeController {
	@Autowired
	private EntreeRepository entreeRepository;

	@GetMapping("find/{id}")
	public Entree findById(@PathVariable(required = true) String id) {
		return entreeRepository.findById(Integer.parseInt(id));
	}
	
	@GetMapping("all")
	public List<Entree> findAll(){
		return entreeRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Entree Entree) {
		entreeRepository.save(Entree);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Entree entree = entreeRepository.findById(Integer.parseInt(id));
		entreeRepository.delete(entree);
	}
	
	@GetMapping("count")
	public long count() {
		return entreeRepository.count();
	}
}
