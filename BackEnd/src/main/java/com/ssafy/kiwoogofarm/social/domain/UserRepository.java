package com.ssafy.kiwoogofarm.social.domain;
import com.ssafy.kiwoogofarm.recipe.domain.RecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
