package com.ssafy.kiwoogofarm.recipe.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
//    List<Recipe> findAll();
//    Recipe findById(Long id);
    List<Recipe> findAllByNameContaining(String keyword);
}
