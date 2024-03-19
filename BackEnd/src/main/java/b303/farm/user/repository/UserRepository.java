package b303.farm.user.repository;


import b303.farm.user.SocialType;
import b303.farm.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByRefreshToken(String refresToken);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
