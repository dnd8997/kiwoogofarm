package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetail;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipe(Long id);
    List<RecipeDetail> getRecipeDetailList(Long recipeId);
    List<Recipe> getRecipeListByKeyword(String keyword);
//    List<Recipe> getRecipeListByOption(String ingredient, String cook, String difficulty);
List<Recipe> getRecipeListByOption(List<String> ingredient, String cook, String difficulty);

}
