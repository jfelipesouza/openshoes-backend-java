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
		Category ct= repository.save(category);
		return new CategoryDto(ct.getId(), ct.getType());
	}

	public List<Category> findAllCategories() {
		return repository.findAll();
	}

	public CategoryDto findByIdCategory(Long id) {
		Optional<Category> op = repository.findById(id);
		Category category = op.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
		return new CategoryDto(category);
	}


	public CategoryDto updateCategory(Long id, Category category) {
		CategoryDto category1 = findByIdCategory(id);
		Category ct= repository.save(category);
		category1.setType(ct.getType());
		return category1;

	}
	
	
	public void deleteCategory(Long id) {
		repository.deleteById(id);
	}

}
