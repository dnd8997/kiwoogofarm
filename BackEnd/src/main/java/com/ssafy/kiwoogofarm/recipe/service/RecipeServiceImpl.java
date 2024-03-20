package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.recipe.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
    public List<RecipeDetail> getRecipeDetailList(Long recipeId) {
        return recipeDetailRepository.findAllByRecipeId(recipeId);
    }

    public List<Recipe> getRecipeListByKeyword(String keyword) {
        return recipeRepository.findAllByNameContaining(keyword);
    }

    public List<Recipe> getRecipeListByOption(List<String> ingredients, String cook, String difficulty) {
//        Specification<Recipe> spec = Specification.where(RecipeSpecification.containingIngredients(ingredients));
//        if(cook!=null) spec = spec.and(RecipeSpecification.containingCook(cook));
//        if(difficulty!=null) spec = spec.and(RecipeSpecification.containingDifficulty(difficulty));
//        return recipeRepository.findAll(spec);
        Specification<Recipe> spec = Specification.where(RecipeSpecification.containingIngredients(ingredients.get(0)));
        for(String ingredient : ingredients) {
            spec = spec.and(RecipeSpecification.containingIngredients(ingredient));
        }
        if(cook!=null) spec = spec.and(RecipeSpecification.containingCook(cook));
        if(difficulty!=null) spec = spec.and(RecipeSpecification.containingDifficulty(difficulty));
        return recipeRepository.findAll(spec);
    }

}
