package b303.farm.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Builder
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    // auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="email")
    private String email;   // 이메일
    @Column(name="password")
    private String password;    // 비밀번호
    @Column(name="nickname")
    private String nickname;
    // 닉네임
    @Column(name="image_url")
    private String imageUrl;    // 프로필 이미지

    @Column(name="age")
    private int age;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;  //카카오, 네이버, 구글

    @Column(name="social_id")
    private String socialId;    //로그인한 소셜 타입의 식별자 값

    @Column(name="refresh_token")
    private String refreshToken;    // 리프레시 토큰

    // 유저 권한 설정 메서드
    public void authorizeUser(){
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메서드
    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken){
        this.refreshToken = updateRefreshToken;
    }
}