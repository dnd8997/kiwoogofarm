package b303.farm.service;

import b303.farm.entity.User;
import b303.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // 회원 등록 로직
        return userRepository.save(user);
    }

    public User getUserInfo(Long userId) {
        // 회원 조회 로직
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserInfo(Long userId, User updatedUser) {
        // 회원 정보 수정 로직
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setSex(updatedUser.getSex());
            user.setAgeRange(updatedUser.getAgeRange());
            user.setAddress(updatedUser.getAddress());
            userRepository.save(user);
        }
        return user;
    }
}
