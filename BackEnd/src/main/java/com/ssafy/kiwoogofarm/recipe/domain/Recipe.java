package com.ssafy.kiwoogofarm.recipe.domain;

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
    private Long id;

    private Long serialNum;
    private String name;
    private String info;
    private String ingredients;
    private String cook;
    private String image;
    private String difficulty;
    private int likes;

    @Builder(toBuilder = true)
    public Recipe(Long id, Long serialNum, String name, String info, String ingredients, String cook, String image, String difficulty, int likes) {
        this.id = id;
        this.serialNum = serialNum;
        this.name = name;
        this.info = info;
        this.ingredients = ingredients;
        this.cook = cook;
        this.image = image;
        this.difficulty = difficulty;
        this.likes = likes;
    }
}
