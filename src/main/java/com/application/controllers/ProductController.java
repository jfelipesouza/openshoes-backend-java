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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.application.entities.Product;
import com.application.services.ProductService;
import com.application.services.dto.ProductDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping("/products")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody Product product) {
		ProductDto pdDto = service.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(pdDto);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = service.findAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/products/{idproduct}")
	public ResponseEntity<ProductDto> getProductId(@PathVariable("idproduct") Long idproduct) {
		ProductDto pdDto = service.findByIdProduct(idproduct);
		return ResponseEntity.status(HttpStatus.OK).body(pdDto);
	}

	@PutMapping("/products")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product) {
		ProductDto pd = service.updateProduct(product);
		return ResponseEntity.status(HttpStatus.OK).body(pd);
	}

	@DeleteMapping("/products/{idproduct}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("idproduct") Long idproduct) {
		service.deleteProduct(idproduct);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/products/type/{type}")
	public ResponseEntity<List<Product>> getProductCategory(@PathVariable("type") String type) {
		return ResponseEntity.ok(service.consultCategory(type));
	}

	@GetMapping("/products/scroll")
	public ResponseEntity<List<Product>> getLimitListProduct(@RequestParam int limit) {
		List<Product> res = service.consultProductList(limit);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
