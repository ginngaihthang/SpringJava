package com.mmit.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmit.entities.Category;
@Repository
public interface Categoryrepo extends JpaRepository<Category, Integer>{

}
