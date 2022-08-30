package com.application.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.application.entities.Category;
import com.application.entities.Product;
import com.application.repository.ProductRepository;
import com.application.services.ProductService;
import com.application.services.dto.ProductDto;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	private Long existingId;
	private Long nonExistingId;
	private Product validProduct;
	private Product invalidProduct;
	private Category validCategory;
	private Category invalidCategory;

	@BeforeEach
	void setup() {
		existingId = 1l;
		nonExistingId = 100000l;
		Integer[] size = { 34, 35, 36 };
		validCategory = new Category("Social");
		invalidCategory = new Category(null);
		validProduct = new Product("Nike", 200.00, size, "url_da_imagem", validCategory, "José");
		invalidProduct = new Product("Nike", 200.00, size, "url_da_imagem", invalidCategory, "José");

		Mockito.when(repository.save(validProduct)).thenReturn(validProduct);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(invalidProduct);

		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(nonExistingId);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(new Product()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(nonExistingId);

	}

	@InjectMocks
	ProductService service;

	@Mock
	ProductRepository repository;

	@Test
	public void returnProductWhenSave() {
		ProductDto product = service.saveProduct(validProduct);
		Assertions.assertNotNull(product);
		Mockito.verify(repository).save(validProduct);
	}

	@Test
	public void returnsErrorWhenSavingProduct() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.saveProduct(invalidProduct);
		});
		Mockito.verify(repository).save(invalidProduct);
	}

	@Test
	public void returnsNothingWhenDeleting() {
		Assertions.assertDoesNotThrow(() -> {
			service.deleteProduct(existingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}

	@Test
	public void entityNotFoundWhenDeletingNonExistingId() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.deleteProduct(nonExistingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}

	@Test
	public void returnsProductWhenSearchForId() {
		Product product = service.findByIdProd(existingId);
		Assertions.assertNotNull(product);
		Mockito.verify(repository).findById(existingId);
	}

	@Test
	public void entityNotFoundWhenSearchingNonExistingId() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.findByIdProduct(nonExistingId);
		});
		Mockito.verify(repository).findById(nonExistingId);

	}

}
