package umc.spring.user.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.mapping.domain.UserMission;
import umc.spring.restaurant.domain.enums.FoodCategory;

@Getter
@Builder
public class UserMissionResponseDTO {
    private Long missionId;
    private String restaurantName;
    private String missionContent;
    private Integer missionScore;
    private boolean isComplete;

    public static UserMissionResponseDTO from(UserMission userMission) {
        return UserMissionResponseDTO.builder()
                .missionId(userMission.getId())
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .missionContent(userMission.getMission().getContent())
                .missionScore(userMission.getMission().getScore())
                .isComplete(userMission.isComplete())
                .build();
    }
}
