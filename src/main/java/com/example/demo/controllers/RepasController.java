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

import com.example.demo.entities.Repas;
import com.example.demo.repositories.RepasRepository;

@RestController
@RequestMapping("api/repas")
public class RepasController {
	@Autowired
	private RepasRepository repasRepository;

	@GetMapping("find/{id}")
	public Repas findById(@PathVariable(required = true) String id) {
		return repasRepository.findById(Integer.parseInt(id));
	}
	
	@GetMapping("all")
	public List<Repas> findAll(){
		return repasRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Repas repas) {
		repasRepository.save(repas);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Repas repas = repasRepository.findById(Integer.parseInt(id));
		repasRepository.delete(repas);
	}
	
	@GetMapping("count")
	public long count() {
		return repasRepository.count();
	}
}
