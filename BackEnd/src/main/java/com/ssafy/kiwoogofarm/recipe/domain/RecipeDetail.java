package com.ssafy.kiwoogofarm.recipe.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int recipe_order;
    private String info;
    private String image;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeDetail(Long id, int recipe_order, String info, String image, Recipe recipe) {
        this.id = id;
        this.recipe_order = recipe_order;
        this.info = info;
        this.image = image;
        this.recipe = recipe;
    }
}
