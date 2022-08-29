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
import com.application.services.dto.CategoryDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
@Api(value = "Rota das categorias",description = "Rotas das categorias")	

public class CategoryController {

	@Autowired
	CategoryService service;
	
	@GetMapping("/categories")
	@ApiOperation(value="Retorna a lista completa contendo todas as categorias")
	public ResponseEntity <List<Category>> getCategory(){
		List<Category> categories= service.findAllCategories();
		return ResponseEntity.ok().body(categories);	
	}
	
	
	@PostMapping("/categories")
	@ApiOperation(value="Cadastra uma nova categoria")
	public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto category){
		Category category1 = new Category(category.getType());
		CategoryDto category2= service.saveCategory(category1);
		return ResponseEntity.status(HttpStatus.CREATED).body(category2);
	}	

	@GetMapping("/categories/{idcategory}")
	@ApiOperation(value="Retorna a categoria consultando-a pelo id")
	public ResponseEntity<Category> getCategoryId(@PathVariable("idcategory") Long idcategory){
		return ResponseEntity.ok(service.findByIdCategory(idcategory));
	}
	
	@PutMapping("/categories/{idcategory}")
	@ApiOperation(value="Atualiza a categoria de acordo com seu id")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("idcategory") Long idcategory, @RequestBody CategoryDto categoryDto){
		Category category = new Category(categoryDto.getType());
		return ResponseEntity.ok(service.updateCategory(idcategory, category));
	}
	
	@DeleteMapping("/categories/{idcategory}")
	@ApiOperation(value="Apaga categoria selecionada pelo id")
	public ResponseEntity<Category> deleteCategory(@PathVariable("idcategory") Long idcategory){
		service.deleteCategory(idcategory);
		return ResponseEntity.noContent().build();
	}
}
