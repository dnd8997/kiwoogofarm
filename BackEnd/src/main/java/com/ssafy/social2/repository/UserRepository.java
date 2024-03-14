package com.ssafy.social2.repository;


import com.ssafy.social2.SocialType;
import com.ssafy.social2.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByRefreshToken(String refresToken);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
