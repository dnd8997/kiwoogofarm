package b303.farm.crop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LikeDto {
    private Long id;
    private boolean like; // true면 좋아요, false면 좋아요 취소

}
