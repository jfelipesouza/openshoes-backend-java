package com.application.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.entities.Category;
import com.application.repository.CategoryRepository;
import com.application.services.dto.CategoryDto;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	public CategoryDto saveCategory(Category category) {
		repository.save(category);
		CategoryDto categoryDto = new CategoryDto(category);
		return categoryDto;
	}

	public List<Category> findAllCategories() {
		List<Category> categories = repository.findAll();
		return categories;
	}

	public Category findByIdCategory(Long id) {
		Optional<Category> op = repository.findById(id);
		Category category = op.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
		return category;
	}



	public CategoryDto updateCategory(Long id, Category category) {
		Category category1 = findByIdCategory(id);
		category1.setType(category.getType());
		repository.save(category1);
		CategoryDto categoryDto = new CategoryDto(category1);
		return categoryDto;

	}

	public void deleteCategory(Long id) {
		repository.deleteById(id);
	}

}
