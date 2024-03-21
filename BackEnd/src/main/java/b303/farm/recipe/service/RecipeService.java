package b303.farm.recipe.service;

import b303.farm.recipe.domain.Recipe;
import b303.farm.recipe.domain.RecipeDetail;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipe(Long id);

    String favoriteRecipe(Long id);

    List<Recipe> getMyFavoriteRecipes();

    List<RecipeDetail> getRecipeDetailList(Long recipeId);
    List<Recipe> getRecipeListByKeyword(String keyword);
//    List<Recipe> getRecipeListByOption(String ingredient, String cook, String difficulty);
List<Recipe> getRecipeListByOption(List<String> ingredient, String cook, String difficulty);

}
