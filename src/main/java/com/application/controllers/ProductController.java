package com.application.controllers;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
@Api(value = "Rota dos produtos",description = "Rotas de produtos")	

public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping("/products")
	@ApiOperation(value="Cadastra novo produto")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody Product product) {
		ProductDto pdDto = service.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(pdDto);
	}

	@GetMapping("/products")
	@ApiOperation(value="Lista todos os produtos")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = service.findAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/products/{idproduct}")
	@ApiOperation(value="Busca produto pelo id")
	public ResponseEntity<ProductDto> getProductId(@PathVariable("idproduct") Long idproduct) {
		ProductDto pdDto = service.findByIdProduct(idproduct);
		return ResponseEntity.status(HttpStatus.OK).body(pdDto);
	}

	@PutMapping("/products/{idproduct}")
	@ApiOperation(value="Atualiza produto pelo id")
	public ResponseEntity<ProductDto> updateProduct(@Valid @PathVariable("idproduct") Long idproduct,@RequestBody Product product) {
		ProductDto pd = service.updateProduct(idproduct, product);
		return ResponseEntity.status(HttpStatus.OK).body(pd);
	}

	@DeleteMapping("/products/{idproduct}")
	@ApiOperation(value="Apaga produto pelo id")
	public ResponseEntity<Product> deleteProduct(@PathVariable("idproduct") Long idproduct) {
		service.deleteProduct(idproduct);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/products/type/{type}")
	@ApiOperation(value="Busca todos os produtos de uma categoria")
	public ResponseEntity<List<Product>> getProductCategory(@PathVariable("type") String type) {
		return ResponseEntity.ok(service.consultCategory(type));
	}

	@GetMapping("/products/scroll")
	@ApiOperation(value="Busca quantidades especificas de produtos por requisição")
	public ResponseEntity<List<Product>> getLimitListProduct(@RequestParam int limit) {
		List<Product> res = service.consultProductList(limit);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	@GetMapping("/products/model/{model}")
	public ResponseEntity<List<Product>> getProductByModel(@PathVariable("model") String model) {
		return ResponseEntity.ok(service.consultProducts(model));
	}
}
