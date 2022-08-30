package com.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.application.entities.Category;
import com.application.services.ProductService;
import com.application.services.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
	private Long existingId;
	private Long nonExistingId;
	private ProductDto validProduct;
	private ProductDto newProduct;
	private Category validCategory;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Autowired
	private ObjectMapper objMapper;

	@BeforeEach
	void setup() {
		existingId = 1L;
		nonExistingId = 1000L;
		validCategory = new Category("Casual");

		newProduct = new ProductDto();
		Integer[] size = { 34, 35, 36 };
		validProduct = new ProductDto(existingId, "Nike", 200.00, size, "url_da_imagem", validCategory.getType(),
				"José");

		Mockito.when(service.findByIdProduct(existingId)).thenReturn(validProduct);
		Mockito.doThrow(EntityNotFoundException.class).when(service).findByIdProduct(nonExistingId);
		Mockito.when(service.saveProduct(any())).thenReturn(validProduct);
		Mockito.when(service.findAllProducts()).thenReturn(new ArrayList<>());

		Mockito.when(service.updateProduct(eq(existingId), any())).thenReturn(validProduct);
		Mockito.when(service.updateProduct(eq(nonExistingId), any())).thenThrow(EntityNotFoundException.class);

	}

	@Test
	void returnsProductWhenExistingId() throws Exception {
		ResultActions result = mockMvc
				.perform(get("/products/{idproduct}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	void returnsStatus404WhenConsultsNonExistingId() throws Exception {
		ResultActions result = mockMvc
				.perform(get("/products/{idproduct}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}

	@Test
	void returnProductWhenSave() throws Exception {
		String jsonBody = objMapper.writeValueAsString(newProduct);
		ResultActions result = mockMvc.perform(post("/products").content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}

	@Test
	void returnsListWhenConsultsAllSuccessfully() throws Exception {
		ResultActions result = mockMvc.perform(get("/products"));
		result.andExpect(status().isOk());
	}

	@Test
	void returnsStatus201WhenUpdateSuccessfully() throws Exception {
		String jsonBody = objMapper.writeValueAsString(validProduct);
		ResultActions result = mockMvc.perform(put("/products/{idproduct}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	void returnsStatus404WhenUpdatesNonExistingProduct() throws Exception {
		String jsonBody = objMapper.writeValueAsString(validProduct);
		ResultActions result = mockMvc.perform(put("/products/{idproduct}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());

	}

}
