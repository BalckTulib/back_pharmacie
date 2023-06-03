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

import com.example.demo.entities.Plat;
import com.example.demo.repositories.PlatRepository;

@RestController
@RequestMapping("api/plat")
public class PlatController {
	@Autowired
	private PlatRepository platRepository;

	@GetMapping("find/{id}")
	public Plat findById(@PathVariable(required = true) String id) {
		return platRepository.findById(Integer.parseInt(id));
	}
	
	@GetMapping("all")
	public List<Plat> findAll(){
		return platRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Plat plat) {
		platRepository.save(plat);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Plat plat = platRepository.findById(Integer.parseInt(id));
		platRepository.delete(plat);
	}
	
	@GetMapping("count")
	public long count() {
		return platRepository.count();
	}
}
