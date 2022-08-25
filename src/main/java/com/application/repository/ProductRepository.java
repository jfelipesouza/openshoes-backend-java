package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select pd from Product pd, Category ct where pd.category = ct.id and ct.type = ?1")
	List<Product> consultCategory (String type);
//	@Query("select * from Product limit ?1 offset ?2 ")
//	List<Product> consultProductList(int limit, int offset);
	
}
