package b303.farm.user.dto;

import lombok.Getter;

@Getter
public class UserInfoDTO {
    private final Long id;
    private final String nickname;

    public UserInfoDTO(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
