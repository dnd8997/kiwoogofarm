package com.ssafy.kiwoogofarm.recipe.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private Long id;

    private Long serialNum;
    private String name;
    private String info;
    private String ingredients;
    private String cook;
    private String image;
    private String difficulty;
    private int likes;

    @OneToMany(mappedBy = "recipe")
    @JsonManagedReference
    @OrderBy("recipe_order")
    private List<RecipeDetail> recipeDetailList = new ArrayList<>();


    @Builder(toBuilder = true)
    public Recipe(Long id, Long serialNum, String name, String info, String ingredients, String cook, String image, String difficulty, int likes, List<RecipeDetail> recipeDetailList) {
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
