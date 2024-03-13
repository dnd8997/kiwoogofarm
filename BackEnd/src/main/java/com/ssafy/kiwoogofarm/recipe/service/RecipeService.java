package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipe(Long id);

}
