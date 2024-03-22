package b303.farm.recipe.controller;

import b303.farm.recipe.service.RecipeService;
import b303.farm.recipe.domain.Recipe;
import b303.farm.recipe.dto.RecipeDto;
import b303.farm.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                    .serialNum(r.getSerialnum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage_url())
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
        RecipeDto recipeDto = RecipeDto.builder()
                .id(recipe.getId())
                .serialNum(recipe.getSerialnum())
                .name(recipe.getName())
                .info(recipe.getInfo())
                .ingredients(recipe.getIngredients())
                .cook(recipe.getCook())
                .image(recipe.getImage_url())
                .difficulty(recipe.getDifficulty())
                .likes(recipe.getLikes())
//                .recipeDetailList(recipe.getRecipeDetailList())
                .recipeDetailList(recipeService.getRecipeDetailList(recipe.getId()))
                .build();
        log.info("레시피 상세 정보: {}", recipeDto);
        return ResponseEntity.ok().body(recipeDto);
    }

    //즐겨찾기 설정/해제
    @PostMapping("/{id}/favorites")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> favoriteRecipe(@PathVariable final Long id,@AuthenticationPrincipal User user) {
        String responseMessage = recipeService.favoriteRecipe(id,user);
        return ResponseEntity.ok(responseMessage);
    }

    //내가 찜한 레시피 조회
    @GetMapping("/myFavorites")
    public ResponseEntity<List<RecipeDto>> getMyRecipeFavorites(@AuthenticationPrincipal User user) {
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        List<Recipe> myFavoriteRecipes = recipeService.getMyFavoriteRecipes(user);

        log.info("유저가 즐겨찾기한 레시피 정보");
        for (Recipe r : myFavoriteRecipes) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .id(r.getId())
                    .serialNum(r.getSerialnum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage_url())
                    .difficulty(r.getDifficulty())
                    .likes(r.getLikes())
                    .build();
            log.info("즐겨찾기한 레시피 정보: {}", recipeDto);
            recipeDtoList.add(recipeDto);
        }
        return ResponseEntity.ok().body(recipeDtoList);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<RecipeDto>> getRecipeByKeyword(@PathVariable String keyword) {
        List<Recipe> recipeList = recipeService.getRecipeListByKeyword(keyword);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        log.info("해당 제목을 포함한 레시피 정보");
        for(Recipe r : recipeList) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .id(r.getId())
                    .serialNum(r.getSerialnum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage_url())
                    .difficulty(r.getDifficulty())
                    .likes(r.getLikes())
                    .build();
            log.info("레시피 정보: {}", recipeDto);
            recipeDtoList.add(recipeDto);
        }
        return ResponseEntity.ok().body(recipeDtoList);
    }

    @GetMapping("/search/option")
    public ResponseEntity<List<RecipeDto>> getRecipeByIngredients(@RequestParam(value = "ingredients") List<String> ingredients, @RequestParam(value = "cook") String cook, @RequestParam(value = "difficulty") String difficulty) {
        List<Recipe> recipeList = recipeService.getRecipeListByOption(ingredients, cook, difficulty);
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        log.info("해당 옵션을 포함한 레시피 정보");
        for(Recipe r : recipeList) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .id(r.getId())
                    .serialNum(r.getSerialnum())
                    .name(r.getName())
                    .info(r.getInfo())
                    .ingredients(r.getIngredients())
                    .cook(r.getCook())
                    .image(r.getImage_url())
                    .difficulty(r.getDifficulty())
                    .likes(r.getLikes())
                    .build();
            log.info("레시피 정보: {}", recipeDto);
            recipeDtoList.add(recipeDto);
        }
        return ResponseEntity.ok().body(recipeDtoList);
    }

}
