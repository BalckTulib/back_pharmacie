package com.example.demo.entities;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Entree extends Repas {
	private String cuisson;

	public Entree() {
		super();
	}

	public Entree(String nom, double prix, String cuisson) {
		super(nom, prix);
		this.cuisson = cuisson;
	}

	public String getCuisson() {
		return cuisson;
	}

	public void setCuisson(String cuisson) {
		this.cuisson = cuisson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cuisson);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entree other = (Entree) obj;
		return Objects.equals(cuisson, other.cuisson);
	}

}
