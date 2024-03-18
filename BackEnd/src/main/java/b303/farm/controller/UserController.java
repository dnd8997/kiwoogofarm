package b303.farm.controller;

import b303.farm.entity.User;
import b303.farm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원 등록
    @PostMapping("/regist")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // 회원 조회
    @GetMapping("/userinfo")
    public User getUserInfo(@RequestParam Long userId) {
        return userService.getUserInfo(userId);
    }

    // 마이페이지에서 회원 정보 조회
    @GetMapping("/mypage/{userId}")
    public User getMyPageUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

//    // 마이페이지에서 회원 정보 수정
//    @PostMapping("/mypage/update/{userId}")
//    public User updateMyPageUserInfo(@PathVariable Long userId, @RequestBody User updatedUser) {
//        return userService.updateUserInfo(userId, updatedUser);
//    }
}
