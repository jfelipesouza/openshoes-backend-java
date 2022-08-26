package com.application.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
