package b303.farm.user.controller;
import b303.farm.user.service.MyPageService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/{email}")
    public String getUserInfoByEmail(@PathVariable String email) {
        return myPageService.getUserInfoByEmail(email);
    }
}
