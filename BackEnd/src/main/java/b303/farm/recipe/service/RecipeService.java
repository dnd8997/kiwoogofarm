package b303.farm.recipe.service;

import b303.farm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipe(Long id);

    String favoriteRecipe(Long id);

    List<Recipe> getMyFavoriteRecipes();
}
