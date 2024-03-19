package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.exception.MemberNotFoundException;
import com.ssafy.kiwoogofarm.exception.RecipeNotFoundException;
import com.ssafy.kiwoogofarm.mypage.FavoriteRecipeRepository;
import com.ssafy.kiwoogofarm.mypage.RecipeFavorites;
import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetailRepository;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeRepository;
import com.ssafy.kiwoogofarm.social.domain.User;
import com.ssafy.kiwoogofarm.social.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDetailRepository recipeDetailRepository;
    private final UserRepository userRepository;
    private final FavoriteRecipeRepository favoriteRecipeRepository;
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
        return recipeRepository.findById(id).orElse(null).toBuilder().recipeDetailList(recipeDetailRepository.findAllByRecipeId(id)).build();
    }

    @Override
    public String favoriteRecipe(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);
        User user = userRepository.findByNickname("sooji"); //임의-> 수정
        if(favoriteRecipeRepository.findByRecipeAndUser(recipe,user)==null){
            RecipeFavorites recipeFavorites = new RecipeFavorites(recipe,user); //생성->true설정
            favoriteRecipeRepository.save(recipeFavorites);
            return "즐겨찾기 처리 완료";
        } else {
            RecipeFavorites recipeFavorites = favoriteRecipeRepository.findByRecipe(recipe);
            recipeFavorites.unFavoriteRecipe(user);
            favoriteRecipeRepository.delete(recipeFavorites);  //status만 바꾸고 지우면 안되는건가??
            return "즐겨찾기 취소";
        }
    }
}
