package com.example.demo.controllers;

import java.util.ArrayList;
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
import com.example.demo.entities.Zone;
import com.example.demo.repositories.VilleRepository;
import com.example.demo.repositories.ZoneRepository;

@RestController
@RequestMapping("api/zone")
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private VilleRepository villeRepository;

	@GetMapping("find/{id}")
	public Zone findById(@PathVariable(required = true) String id) {
		return zoneRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("all")
	public List<Zone> findAll() {
		return zoneRepository.findAll();
	}

	@PostMapping("save")
	public void save(@RequestBody Zone zone) {
		zoneRepository.save(zone);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Zone zone = zoneRepository.findById(Integer.parseInt(id));
		zoneRepository.delete(zone);
	}

	@GetMapping("count")
	public long count() {
		return zoneRepository.count();
	}
	
	@GetMapping("byVille/{villeId}")
	public List<Zone> zonesByVille(@PathVariable(required = true) String villeId){
		Ville ville = villeRepository.findById(Integer.parseInt(villeId));
		List<Zone> zones = new ArrayList<Zone>();
		for(Zone z : zoneRepository.findAll())
			if(z.getVille().equals(ville))
				zones.add(z);
		return zones;
	}
}
