package com.example.demo.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@ManyToOne
	private Ville ville;

	public Zone() {
		super();
	}

	public Zone(String nom, Ville ville) {
		super();
		this.nom = nom;
		this.ville = ville;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Zone [id=" + id + ", nom=" + nom + ", ville=" + ville + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nom, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zone other = (Zone) obj;
		return id == other.id && Objects.equals(nom, other.nom) && Objects.equals(ville, other.ville);
	}

	@JsonIgnore
	public Boolean isEmpty() {
		if (this.id == 0 && this.nom == null && this.ville == null)
			return true;
		return false;
	}
}
