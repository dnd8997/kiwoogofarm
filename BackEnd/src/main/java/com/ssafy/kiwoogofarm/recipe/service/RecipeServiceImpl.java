package com.ssafy.kiwoogofarm.recipe.service;

import com.ssafy.kiwoogofarm.exception.MemberNotFoundException;
import com.ssafy.kiwoogofarm.exception.RecipeNotFoundException;
import com.ssafy.kiwoogofarm.mypage.FavoriteRecipeRepository;
import com.ssafy.kiwoogofarm.mypage.RecipeFavorites;
import com.ssafy.kiwoogofarm.recipe.controller.RecipeController;
import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetailRepository;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeRepository;
import com.ssafy.kiwoogofarm.social.domain.User;
import com.ssafy.kiwoogofarm.social.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDetailRepository recipeDetailRepository;
    private final UserRepository userRepository;
    private final FavoriteRecipeRepository favoriteRecipeRepository;

    private Logger log = LoggerFactory.getLogger(RecipeController.class);

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
//    @Transactional  //class단위로 넣어야 되는지?
    public String favoriteRecipe(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);
        User user = userRepository.findByNickname("sooji"); //임의-> 수정

        //db에서 recipe,user가 둘다 같은 데이터가 있는지 조회한다(true,false불문)
        RecipeFavorites recipeFavorites = favoriteRecipeRepository.findByRecipeAndUser(recipe, user);
        //만약 없다면, 생성하고 저장한다.
        if(recipeFavorites == null){
            recipeFavorites = RecipeFavorites.createRecipeFavorites(recipe, user);
            favoriteRecipeRepository.save(recipeFavorites);
            return "즐겨찾기 신규설정";  //다른 값?
        } else {
            //만약 존재한다면
            //그리고 status값이 true라면, status를 false로 바꾸고,연관관계도 끊고,좋아요수도 증감시킨다.
            if (recipeFavorites.isStatus()){
                recipeFavorites.unFavoriteRecipe();
//                favoriteRecipeRepository.save(recipeFavorites);

                log.info("리스트 확인 " + user.getRecipeFavoritesList());//.get(0).getId()
                return "즐겨찾기 취소";
            } else {
                //status값이 false라면,status를 true로 바꾸고, 연관관계도 만들고, 좋아요 수도 증가시킨다.
                recipeFavorites.reFavoriteRecipe();
//                favoriteRecipeRepository.save(recipeFavorites);
                log.info("리스트 확인" + user.getRecipeFavoritesList());//.get(0).getId()
                return "즐겨찾기 재설정";
            }
        }
    }

    @Override
//    @Transactional
    public List<Recipe> getMyFavoriteRecipes() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);
        User user = userRepository.findByNickname("sooji"); //임의-> 수정
        List<RecipeFavorites> myFavoriteRecipeList = user.getRecipeFavoritesList();
        log.info("즐겨찾기리스트 확인" + user.getRecipeFavoritesList());//.get(0).getId()
        List<Recipe> myFavoriteRecipes = new ArrayList<>();
        for (RecipeFavorites recipeFavorites : myFavoriteRecipeList) {
            myFavoriteRecipes.add(recipeFavorites.getRecipe());
        }
        return myFavoriteRecipes;
    }
}
