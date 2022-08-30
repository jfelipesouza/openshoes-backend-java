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
import com.application.services.CategoryService;
import com.application.services.dto.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
	private Long existingId;
	private Long nonExistingId;
	private CategoryDto existingCategory;
	private CategoryDto newCategory;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CategoryService service;

	@Autowired
	private ObjectMapper objMapper;

	@BeforeEach
	void setup() {
		existingId = 1L;
		nonExistingId = 100L;
		existingCategory = new CategoryDto(existingId, "Casual");
		newCategory = new CategoryDto();

		Mockito.when(service.findByIdCategory(existingId)).thenReturn(existingCategory);
		Mockito.doThrow(EntityNotFoundException.class).when(service).findByIdCategory(nonExistingId);
		Mockito.when(service.saveCategory(any())).thenReturn(existingCategory);
		Mockito.when(service.updateCategory(eq(existingId), any())).thenReturn(existingCategory);
		Mockito.when(service.updateCategory(eq(nonExistingId), any())).thenThrow(EntityNotFoundException.class);
		Mockito.when(service.findAllCategories()).thenReturn(new ArrayList<>());
	}

	@Test
	void returnsCategoryWhenExistingId() throws Exception {
		ResultActions result = mockMvc
				.perform(get("/categories/{idcategory}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	void returnsStatus404WhenConsultsNonExistingId() throws Exception {
		ResultActions result = mockMvc
				.perform(get("/categories/{idcategory}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}

	@Test
	void returnCategoryWhenSave() throws Exception {
		String jsonBody = objMapper.writeValueAsString(newCategory);
		ResultActions result = mockMvc.perform(post("/categories").content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}

	@Test
	void returnsStatus201WhenUpdateSuccessfully() throws Exception {
		String jsonBody = objMapper.writeValueAsString(existingCategory);
		ResultActions result = mockMvc.perform(put("/categories/{idcategory}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	void returnsStatus404WhenUpdatesNonExistingCategory() throws Exception {
		String jsonBody = objMapper.writeValueAsString(existingCategory);
		ResultActions result = mockMvc.perform(put("/categories/{idcategory}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());

	}

	@Test
	void returnsListWhenConsultsAllSuccessfully() throws Exception {
		ResultActions result = mockMvc.perform(get("/categories"));
		result.andExpect(status().isOk());
	}
}
