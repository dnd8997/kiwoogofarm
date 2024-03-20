package com.ssafy.kiwoogofarm.recipe.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
//    List<Recipe> findAll();
//    Recipe findById(Long id);
    List<Recipe> findAllByNameContaining(String keyword);

}
