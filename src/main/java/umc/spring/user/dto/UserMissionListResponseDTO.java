package umc.spring.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import umc.spring.mapping.domain.UserMission;
import umc.spring.restaurant.domain.enums.FoodCategory;

import java.util.List;

@Getter
@Builder
public class UserMissionListResponseDTO {
    private List<UserMissionResponseDTO> missions;
    private Boolean isLast; // 추가 데이터 여부

    public static UserMissionListResponseDTO from(Slice<UserMission> userMissions) {
        List<UserMissionResponseDTO> missions = userMissions.getContent()
                .stream()
                .map(UserMissionResponseDTO::from)
                .toList();

        boolean isLast = userMissions.hasNext();

        return UserMissionListResponseDTO.builder().
                missions(missions)
                .isLast(isLast)
                .build();
    }
}
