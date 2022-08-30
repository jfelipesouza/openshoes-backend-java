package com.application.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max= 50 ,min= 3, message= "Produto não deve ter menos que 3 caracteres")
	@Column(length = 50, nullable = false)
	private String model;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Integer[] size;
	@NotBlank(message= "O produto deve ter uma imagem")
	@Column(nullable= false)
	private String image;
	@NotBlank(message= "Informe código do lojista")
	@Column(nullable= false)
	private String logistCode;
	
	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateAt;
	

	public Instant getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = Instant.now();
	}

	public Instant getUpdateAt() {
		return updateAt;
	}
	@PreUpdate
	public void setUpdateAt() {
		this.updateAt = Instant.now();
	}

	@ManyToOne
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(String model, Double price, Integer[] size, String image, Category category, String logistCode) {
		this.model = model;
		this.price = price;
		this.size = size;
		this.image = image;
		this.category = category;
		this.logistCode= logistCode;
	}

	public Product() {
		
	}
	
	
	
	
	
	
	

}
