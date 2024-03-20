package com.ssafy.kiwoogofarm.recipe.controller;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetail;
import com.ssafy.kiwoogofarm.recipe.dto.RecipeDto;
import com.ssafy.kiwoogofarm.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    private Logger log = LoggerFactory.getLogger(RecipeController.class);

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getRecipeList() {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        log.info("전체 레시피 정보");
        for(Recipe r : recipeList) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .id(r.getId())
                    .serialNum(r.getSerialNum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage())
                    .difficulty(r.getDifficulty())
                    .likes(r.getLikes())
                    .build();
            log.info("레시피 정보: {}", recipeDto);
            recipeDtoList.add(recipeDto);
        }
        return ResponseEntity.ok().body(recipeDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
//        List<RecipeDetail> recipeDetailList = recipeService;
        RecipeDto recipeDto = RecipeDto.builder()
                .id(recipe.getId())
                .serialNum(recipe.getSerialNum())
                .name(recipe.getName())
                .info(recipe.getInfo())
                .ingredients(recipe.getIngredients())
                .cook(recipe.getCook())
                .image(recipe.getImage())
                .difficulty(recipe.getDifficulty())
                .likes(recipe.getLikes())
                .recipeDetailList(recipe.getRecipeDetailList())
                .build();
        log.info("레시피 상세 정보: {}", recipeDto);
        return ResponseEntity.ok().body(recipeDto);
    }

    //즐겨찾기 설정/해제
    @PostMapping("/{id}/favorites")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> favoriteRecipe(@PathVariable final Long id) {
        String responseMessage = recipeService.favoriteRecipe(id);
        return ResponseEntity.ok(responseMessage);
    }

    //내가 찜한 레시피 조회
    @GetMapping("/my/favorites")
    public ResponseEntity<List<RecipeDto>> getMyRecipeFavorites(){
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        List<Recipe> myFavoriteRecipes = recipeService.getMyFavoriteRecipes();

        log.info("유저가 즐겨찾기한 레시피 정보");
        for(Recipe r : myFavoriteRecipes) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .id(r.getId())
                    .serialNum(r.getSerialNum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage())
                    .difficulty(r.getDifficulty())
                    .likes(r.getLikes())
                    .build();
            log.info("즐겨찾기한 레시피 정보: {}", recipeDto);
            recipeDtoList.add(recipeDto);
        }
        return ResponseEntity.ok().body(recipeDtoList);
    }



}
