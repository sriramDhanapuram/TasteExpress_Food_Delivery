package com.tastyExpress.dao;

import java.util.List;

import com.tastyExpress.model.Restaurent;

public interface RestaurentDAO {

	void addRestaurent(Restaurent restaurent);
	
	Restaurent getRestaurent(int restaurentId);
	
	void updateRestaurent(Restaurent restaurent);
	
	void deleteRestaurent(int restaurentId);
	
	List<Restaurent> getAllRestaurents();
}
