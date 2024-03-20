package com.ssafy.kiwoogofarm.social.domain;

import com.ssafy.kiwoogofarm.mypage.RecipeFavorites;
import com.ssafy.kiwoogofarm.social.Role;
import com.ssafy.kiwoogofarm.social.SocialType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Builder
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    // auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String email;   // 이메일
    private String password;    // 비밀번호
    //닉네임 unique설정해주기
    private String nickname;    // 닉네임
    private String imageUrl;    // 프로필 이미지
    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;  //카카오, 네이버, 구글

    private String socialId;    //로그인한 소셜 타입의 식별자 값

    private String refreshToken;    // 리프레시 토큰

    //추가한 부분 start
    @OneToMany(mappedBy = "user")
    private List<RecipeFavorites> recipeFavoritesList= new ArrayList<>();

    //==비즈니스로직 및 (User,RecipeFavorites)연관관계편의 메서드==//
    public void addRecipeFavorite(RecipeFavorites recipeFavorites) {
        recipeFavoritesList.add(recipeFavorites);
        recipeFavorites.setUser(this);
    }
    public void removeRecipeFavorite(RecipeFavorites recipeFavorites) {
        recipeFavoritesList.remove(recipeFavorites);
        //이 부분에 setUser(null)추가하면 안됨.findByRecipeAndUser이 부분에서 조회가 안됨.
    }
    //추가한 부분 end

    // 유저 권한 설정 메서드
    public void authorizeUser(){
        this.role = Role.USER;
    }


//    // 비밀번호 암호화 메서드
//    public void passwordEncode(PasswordEncoder passwordEncoder){
//        this.password = passwordEncoder.encode(this.password);
//    }

//    public void updateRefreshToken(String updateRefreshToken){
//        this.refreshToken = updateRefreshToken;
//    }
}