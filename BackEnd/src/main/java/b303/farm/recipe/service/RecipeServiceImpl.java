package b303.farm.recipe.service;

import b303.farm.recipe.domain.*;
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
        Specification<Recipe> spec = (root, query, criteriaBuilder) -> null;
        for(String ingredient : ingredients) {
            spec = spec.and(RecipeSpecification.containingIngredients(ingredient));
        }
        if(cook!=null) spec = spec.and(RecipeSpecification.containingCook(cook));
        if(difficulty!=null) spec = spec.and(RecipeSpecification.containingDifficulty(difficulty));
        return recipeRepository.findAll(spec);
    }

}
