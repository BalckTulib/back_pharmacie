package com.example.demo.dao;

import java.util.List;

public interface IDao<T> {
	T findById(int id);

	List<T> findAll();

	void save(T object);

	void update(T object);

	void delete(T object);

	long count();
}
