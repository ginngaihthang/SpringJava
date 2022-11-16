package com.mmit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
