package com.application.services.dto;

import java.util.ArrayList;
import java.util.List;
import com.application.entities.Product;

public class ProductDto {
	private String model;
	private Double price;
	private Integer[] size;
	private String image;
	private String typeCategory;
	private String logistCode;



	public ProductDto() {

	}

	public ProductDto(Product product) {
		this.model = product.getModel();
		this.price = product.getPrice();
		this.size =product.getSize();
		this.image = product.getImage();
		this.typeCategory= product.getCategory().getType();
		this.logistCode= product.getLogistCode();
	}

	public ProductDto(String model, Double price, Integer[] size, String image, String typeCategory, String logistCode) {
		this.model = model;
		this.price = price;
		this.size = size;
		this.image = image;
		this.typeCategory= typeCategory;
		this.logistCode= logistCode;
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
