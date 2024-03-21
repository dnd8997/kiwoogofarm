package b303.farm.recipe.dto;

import b303.farm.recipe.domain.RecipeDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeDto {

    private Long id;
    private Long serialNum;
    private String name;
    private String info;
    private String ingredients;
    private String cook;
    private String image;
    private String difficulty;
    private int likes;
    private List<RecipeDetail> recipeDetailList;

    @Builder
    public RecipeDto(Long id, Long serialNum, String name, String info, String ingredients, String cook, String image, String difficulty, int likes, List<RecipeDetail> recipeDetailList) {
        this.id = id;
        this.serialNum = serialNum;
        this.name = name;
        this.info = info;
        this.ingredients = ingredients;
        this.cook = cook;
        this.image = image;
        this.difficulty = difficulty;
        this.likes = likes;
        this.recipeDetailList = recipeDetailList;
    }
}
