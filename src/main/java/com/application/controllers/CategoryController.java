package com.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entities.Category;
import com.application.services.CategoryService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@GetMapping("/categories")
	public ResponseEntity <List<Category>> getCategory(){
		List<Category> categories= service.findAllCategories();
		return ResponseEntity.ok().body(categories);	
	}
	
	
	@PostMapping("/categories")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		Category category1= service.saveCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category1);
	}

	@GetMapping("/categories/{idcategory}")
	public ResponseEntity<Category> getCategoryId(@PathVariable("idcategory") Long idcategory){
		return ResponseEntity.ok(service.findByIdCategory(idcategory));
	}
	
	@PutMapping("/categories/{idcategory}")
	public ResponseEntity<Category> updateCategory(@PathVariable("idcategory") Long idcategory, @RequestBody Category category){
		return ResponseEntity.ok(service.updateCategory(idcategory, category));
	}
	
	@DeleteMapping("/categories/{idcategory}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("idcategory") Long idcategory){
		service.deleteCategory(idcategory);
		return ResponseEntity.noContent().build();
	}
}
