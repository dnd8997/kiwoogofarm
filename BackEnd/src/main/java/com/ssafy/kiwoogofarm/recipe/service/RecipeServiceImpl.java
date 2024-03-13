package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetail;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetailRepository;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDetailRepository recipeDetailRepository;
    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipe(Long id) {
//        List<RecipeDetail> recipeDetailList = recipeDetailRepository.findAllByRecipeId(id);
//        Recipe recipe = recipeRepository.findById(id).orElse(null).toBuilder().recipeDetailList(recipeDetailList).build();
////        recipe = Recipe.builder().recipeDetailList(recipeDetailList).build();
////        recipe = recipe.toBuilder().recipeDetailList(recipeDetailList).build();
//        return recipe;
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public List<RecipeDetail> getRecipieDetailList(Long recipeId) {
        return recipeDetailRepository.findAllByRecipeId(recipeId);
    }

}
