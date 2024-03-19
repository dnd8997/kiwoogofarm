package com.ssafy.kiwoogofarm.mypage;


import com.ssafy.kiwoogofarm.recipe.domain.Recipe;
import com.ssafy.kiwoogofarm.social.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<RecipeFavorites,Long> {
    RecipeFavorites findByRecipeAndUser(Recipe recipe, User user);

    RecipeFavorites findByRecipe(Recipe recipe);
    List<RecipeFavorites> findAllByUser(User user);


}
