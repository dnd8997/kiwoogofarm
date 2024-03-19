package com.ssafy.kiwoogofarm.mypage;

import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.social.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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


    //기본 생성자 메서드
    public RecipeFavorites(){
    }


    //생성자 메서드
    public RecipeFavorites(Recipe recipe, User user){
        this.recipe = recipe;
        this.user=user;
        this.status = true;
//        user.getRecipeFavoritesList().add(this); //이거 생성자 메서드에 넣어도 되나?
    }

    public void unFavoriteRecipe(User user){
        this.status = false;
//        user.getRecipeFavoritesList().remove(this);  //이것도 삭제가 아닌 true,false로 해야 하나??
    }


}
