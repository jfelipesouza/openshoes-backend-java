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
import com.application.repository.CategoryRepository;
import com.application.services.CategoryService;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
	private Long existingId;
	private Long nonExistingId;
	private Category validCategory;
	private Category invalidCategory;
	
	@BeforeEach
	void setup() {
		existingId= 1l;
		nonExistingId= 1000l;
		validCategory= new Category("Social");
		invalidCategory= new Category(null);
		
		Mockito.when(repository.save(validCategory)).thenReturn(validCategory);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(invalidCategory);
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(nonExistingId);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(new Category()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(nonExistingId);
	}
	
	@InjectMocks
	CategoryService service;
	
	@Mock
	CategoryRepository repository;
	
	@Test
	public void returnCategoryWhenSave() {
		Category category= service.saveCategory(validCategory);
		Assertions.assertNotNull(category);
		Mockito.verify(repository).save(validCategory);
	}
	@Test
	public void returnsErrorWhenSavingCategory() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
		service.saveCategory(invalidCategory);
		});
		Mockito.verify(repository).save(invalidCategory);
	}
	
	@Test
	public void returnsNothingWhenDeleting() {
		Assertions.assertDoesNotThrow(()->{
			service.deleteCategory(existingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void entityNotFoundWhenDeletingNonExistingId() {
		Assertions.assertThrows(EntityNotFoundException.class, ()->{
			service.deleteCategory(nonExistingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void returnsCategoryWhenSearchForId() {
		Category category= service.findByIdCategory(existingId);
		Assertions.assertNotNull(category);
		Mockito.verify(repository).findById(existingId);
	}
	
	@Test
	public void entityNotFoundWhenSearchingNonExistingId() {
		Assertions.assertThrows(EntityNotFoundException.class, ()->{
			service.findByIdCategory(nonExistingId);
			});
		Mockito.verify(repository).findById(nonExistingId);
		
	}

	
	

}
