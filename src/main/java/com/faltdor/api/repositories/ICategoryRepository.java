package com.faltdor.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faltdor.api.domain.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
