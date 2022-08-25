package com.application.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.entities.Category;
import com.application.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	public Category saveCategory(Category category) {
		return repository.save(category);
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

	public Category updateCategory(Long id, Category category) {
		Category category1 = findByIdCategory(id);
		category1.setType(category.getType());
		return repository.save(category1);

	}

	public void deleteCategory(Long id) {
		repository.deleteById(id);
	}

}
