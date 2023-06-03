package com.example.demo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private double latitude;
	private double longitude;
	public LocalDate dateCreation;
	@ManyToOne
	private Zone zone;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Repas> repas;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Specialite> specialites;

	public Restaurant() {
		super();
	}

	public Restaurant(String nom, double latitude, double longitude, LocalDate dateCreation, Zone zone,
			List<Repas> repas, List<Specialite> specialites) {
		super();
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateCreation = dateCreation;
		this.zone = zone;
		this.repas = repas;
		this.specialites = specialites;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Repas> getRepas() {
		return repas;
	}

	public void setRepas(List<Repas> repas) {
		this.repas = repas;
	}

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", nom=" + nom + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", dateCreation=" + dateCreation + ", zone=" + zone + ", repas=" + repas + ", specialites="
				+ specialites + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, id, latitude, longitude, nom, repas, specialites, zone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(dateCreation, other.dateCreation) && id == other.id
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& Objects.equals(nom, other.nom) && Objects.equals(repas, other.repas)
				&& Objects.equals(specialites, other.specialites) && Objects.equals(zone, other.zone);
	}
	@JsonIgnore
	public Boolean isEmpty() {
		if(this.id == 0 && this.nom == null && this.latitude == 0.0 && this.longitude == 0 && this.dateCreation == null && this.zone == null && this.repas == null && this.specialites == null)
			return true;
		return false;
	}
}
