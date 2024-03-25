package b303.farm.user.service;
import b303.farm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import b303.farm.user.User;


@Service
public class MyPageService {
    private final UserRepository userRepository;

    @Autowired
    public MyPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserInfoByEmail(String email) {
        User userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // id와 nickname을 사용하여 원하는 형태의 문자열로 반환
        // 예를 들어, "ID: {id}, Nickname: {nickname}" 형태로 반환
        return "id: " + userInfo.getId() + ", nickname: " + userInfo.getNickname();
    }

}
