package com.application.services.dto;

import java.util.ArrayList;
import java.util.List;
import com.application.entities.Product;

public class ProductDto {
	private Long id;
	private String model;
	private Double price;
	private Integer[] size;
	private String image;
	private String typeCategory;
	private String logistCode;
	private Boolean available;



	public ProductDto() {

	}

	public ProductDto(Product product) {
		this.id = product.getId();
		this.model = product.getModel();
		this.price = product.getPrice();
		this.size =product.getSize();
		this.image = product.getImage();
		this.typeCategory= product.getCategory().getType();
		this.logistCode= product.getLogistCode();
		this.available= product.getAvailable();
	}

	public ProductDto(Long id,String model, Double price, Integer[] size, String image, String typeCategory, String logistCode, Boolean available) {
		this.id = id;
		this.model = model;
		this.price = price;
		this.size = size;
		this.image = image;
		this.typeCategory= typeCategory;
		this.logistCode= logistCode;
		this.available= available;
	}



	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}
	
	public String getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer[] getSize() {
		return size;
	}

	public void setSize(Integer[] size) {
		this.size = size;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static List<ProductDto> convertDto(List<Product> products) {
		List<ProductDto> productsDto = new ArrayList<>();
		for (Product pd : products) {
			productsDto.add(new ProductDto(pd));

		}
		return productsDto;
	}

}
