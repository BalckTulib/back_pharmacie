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

import com.example.demo.entities.Ville;
import com.example.demo.repositories.VilleRepository;

@RestController
@RequestMapping("api/ville")
public class VilleController {
	@Autowired
	private VilleRepository villeRepository;

	@GetMapping("find/{id}")
	public Ville findById(@PathVariable(required = true) String id) {
		return villeRepository.findById(Integer.parseInt(id));
	}
	
	@GetMapping("all")
	public List<Ville> findAll(){
		return villeRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Ville ville) {
		villeRepository.save(ville);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Ville ville = villeRepository.findById(Integer.parseInt(id));
		villeRepository.delete(ville);
	}
	
	@GetMapping("count")
	public long count() {
		return villeRepository.count();
	}
}
