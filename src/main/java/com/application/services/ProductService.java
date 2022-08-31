package com.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.application.entities.Product;
import com.application.repository.ProductRepository;
import com.application.services.dto.ProductDto;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public ProductDto saveProduct(Product product) {
		Product pd = repository.save(product);
		return new ProductDto(pd.getId(), pd.getModel(), pd.getPrice(), pd.getSize(), pd.getImage(),
				pd.getCategory().getType(), pd.getLogistCode());

	}

	public List<ProductDto> findAllProducts() {
		List<Product> products = repository.findAll();
		List<ProductDto> productsDtos = new ArrayList<ProductDto>();
		for (Product pd : products) {
			productsDtos.add(new ProductDto(pd));
		}
		return productsDtos;
	}

	public ProductDto findByIdProduct(Long id) {
		Optional<Product> op = repository.findById(id);
		Product product = op.orElseThrow(() -> new EntityNotFoundException("Product não encontrado"));
		return new ProductDto(product);
	}

	public Product findByIdProd(Long id) {
		Optional<Product> op = repository.findById(id);
		return op.orElseThrow(() -> new EntityNotFoundException("Product não encontrado"));

	}

	public ProductDto updateProduct(Long id, Product product) {
		ProductDto product1 = findByIdProduct(id);
		Product pd = repository.save(product);
		product1.setModel(pd.getModel());
		product1.setPrice(pd.getPrice());
		product1.setSize(pd.getSize());
		product1.setImage(pd.getImage());
		product1.setTypeCategory(pd.getCategory().getType());
		product1.setLogistCode(pd.getLogistCode());
		return product1;
	}

	public void deleteProduct(Long id) {
		repository.deleteById(id);

	}

	public List<Product> consultCategory(String type) {
		return repository.consultCategory(type);
	}

	public List<Product> consultProductList(int limit) {
		Pageable page = PageRequest.of(0, limit);
		Page<Product> products = repository.findAll(page);

		return products.getContent();
	}

	public List<Product> consultProducts(String model) {
		return repository.consultProduct(model);
	}

	public List<Product> consultProductsByLogist(String logistCode) {
		return repository.consultProductByLogist(logistCode);
	}

}
