package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Repas;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Specialite;
import com.example.demo.entities.Ville;
import com.example.demo.entities.Zone;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.SpecialiteRepository;
import com.example.demo.repositories.VilleRepository;
import com.example.demo.repositories.ZoneRepository;

@RestController
@RequestMapping("api/restau")
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private SpecialiteRepository specialiteRepository;

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private ZoneRepository zoneRepository;

	@GetMapping("find/{id}")
	public Restaurant findById(@PathVariable(required = true) String id) {
		return restaurantRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("all")
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	@PostMapping("saveEasy")
	public void saveRestau(@RequestBody Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}

	@PostMapping("save")
	public ResponseEntity<String> save(@RequestBody Restaurant restaurant) {
		try {
			if (restaurant.isEmpty())
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request, restau vide !");
			List<Repas> repas = restaurant.getRepas();
			if (repas.isEmpty())
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request, pas de repas !");
			for (Repas r : repas)
				if (r == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request, l'un des repas est vide !");
			for (Restaurant r : restaurantRepository.findAll())
				if (r.getId() == restaurant.getId())
					return ResponseEntity.status(HttpStatus.CONFLICT).body("restau exists!");
			if (restaurantRepository.save(restaurant) != null)
				return ResponseEntity.status(HttpStatus.CREATED).body("restau created");
		} catch (Exception e) {
			//System.out.println("exception !" + e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("creation error!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No action performed for the given request!");
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Restaurant restaurant = restaurantRepository.findById(Integer.parseInt(id));
		restaurantRepository.delete(restaurant);
	}

	@GetMapping("count")
	public long count() {
		return restaurantRepository.count();
	}

	@PostMapping("{restauId}/zone")
	public ResponseEntity<String> addZone(@PathVariable(required = true) String restauId, @RequestBody Zone zone) {
		try {
			Restaurant restaurant = restaurantRepository.findById(Integer.parseInt(restauId));
			if (restaurant.isEmpty() || zone.isEmpty()) {
				//System.out.println(restaurant);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request, zone ou restau vide !");
			}
			Zone zoneOld = restaurant.getZone();
			//System.out.println(zoneOld);
			//System.out.println(zone);
			if (zoneOld != null && zoneOld.equals(zone))
				return ResponseEntity.status(HttpStatus.CONFLICT).body("zone exist!");
			restaurant.setZone(zone);
			if (restaurantRepository.save(restaurant) != null)
				return ResponseEntity.status(HttpStatus.CREATED).body("zone created in restau!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("creation error!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No action performed for the given request!");
	}

	@GetMapping("restaus/specialite/{nom}")
	public ResponseEntity<?> restauWithSpecialite(@PathVariable(required = true) String nom) {
		try {
			Specialite specialite = specialiteRepository.findByNom(nom);
			if (specialite == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("specialite invalide!");
			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			for (Restaurant r : restaurantRepository.findAll())
				if (r.getSpecialites().contains(specialite))
					restaurants.add(r);
			if (restaurants.size() == 0)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("aucun restau n'as cette specialite!");
			else
				return ResponseEntity.status(HttpStatus.OK).body(restaurants);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error!");
		}
	}

	@GetMapping("zone/{zoneId}/spec/{specId}")
	public List<Restaurant> restauVilleZoneSpec(@PathVariable(required = true) String zoneId,
			@PathVariable(required = true) String specId) {
		Zone zone = zoneRepository.findById(Integer.parseInt(zoneId));
		//System.out.println(zone);
		Specialite specialite = specialiteRepository.findById(Integer.parseInt(specId));
		//System.out.println(specialite);
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (Restaurant r : restaurantRepository.findAll())
			if (r.getZone() != null && r.getSpecialites() != null && r.getZone().equals(zone)
					&& r.getSpecialites().contains(specialite))
				restaurants.add(r);
		/*if (restaurants.isEmpty())
			System.out.println("emty list");
		for (Restaurant r : restaurants)
			System.out.println(r);*/
		return restaurants;
	}

	@GetMapping("specs/zone/{zoneId}")
	public HashSet<Specialite> specsZone(@PathVariable(required = true) String zoneId) {
		Zone zone = zoneRepository.findById(Integer.parseInt(zoneId));
		HashSet<Specialite> specialites = new HashSet<Specialite>();
		for (Restaurant r : restaurantRepository.findAll())
			if (r.getZone() != null && r.getZone().equals(zone))
				specialites.addAll(r.getSpecialites());
		return specialites;

	}

}
