package com.application.services.dto;

import com.application.entities.Category;

public class CategoryDto {

	private Long id;
	private String type;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public CategoryDto() {}
	
	public CategoryDto(Category category) {
		this.id= category.getId();
		this.type = category.getType();
	}
	public CategoryDto(Long id, String type) {
		this.id=id;
		this.type = type;
	}
}
