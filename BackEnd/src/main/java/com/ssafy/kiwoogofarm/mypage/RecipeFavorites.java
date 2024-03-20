package com.ssafy.kiwoogofarm.mypage;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.social.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeFavorites {
    @Id @GeneratedValue
    @Column(name="recipe_favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private boolean status ; //erd랑 변수명 다름에 주의. true= 즐겨찾기, false= 즐겨찾기 취소

    //생성 메서드
    public static RecipeFavorites createRecipeFavorites(Recipe recipe, User user){
        RecipeFavorites recipeFavorites = new RecipeFavorites();
        recipeFavorites.setRecipe(recipe);  //단방향이라서 이렇게만 설정해줘도 됨?
        recipeFavorites.setStatus(true);
        user.addRecipeFavorite(recipeFavorites);
        recipe.addLike();
        return recipeFavorites;
    }

    public void unFavoriteRecipe(){
        //favoriteRecipe의 status를 변경하고
        setStatus(false);
        //User와 RecipeFavorite연관관계끊어버리고(단방향으로)
        getUser().removeRecipeFavorite(this);
        //Recipe에 있는 likes++
        getRecipe().removeLike();
    }

    //re를 따로 만들어줘야하나??
    public void reFavoriteRecipe(){
        //여기는 연관관계생성하면 안됨(즐겨찾기 재설정하는데 새로운 필드가 생겨서)
        setStatus(true);
        getRecipe().addLike();
    }
}
