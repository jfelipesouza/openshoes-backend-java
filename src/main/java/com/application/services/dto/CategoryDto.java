package com.application.services.dto;

import com.application.entities.Category;

public class CategoryDto {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public CategoryDto() {}
	
	public CategoryDto(Category category) {
		this.type = category.getType();
	}
	public CategoryDto(String type) {
		this.type = type;
	}
}
