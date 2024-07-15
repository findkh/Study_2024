package com.kh.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Course {
	private long id;
	private String name;
	private String category;
	
	@Min(value = 1, message = "A Course should have a minimum of 1 rating")
	@Max(value = 5, message = "A Course shoud have a maximum of 5 rating")
	private int rating;
	
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
