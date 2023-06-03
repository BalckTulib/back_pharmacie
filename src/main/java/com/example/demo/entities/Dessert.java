package com.example.demo.entities;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Dessert extends Repas {
	private String type;

	public Dessert() {
		super();
	}

	public Dessert(String nom, double prix, String type) {
		super(nom, prix);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(type);
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
		Dessert other = (Dessert) obj;
		return Objects.equals(type, other.type);
	}

}
