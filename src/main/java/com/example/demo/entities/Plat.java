package com.example.demo.entities;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Plat extends Repas {
	private String ingredients;

	public Plat() {
		super();
	}

	public Plat(String nom, double prix, String ingredients) {
		super(nom, prix);
		this.ingredients = ingredients;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ingredients);
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
		Plat other = (Plat) obj;
		return Objects.equals(ingredients, other.ingredients);
	}

}
