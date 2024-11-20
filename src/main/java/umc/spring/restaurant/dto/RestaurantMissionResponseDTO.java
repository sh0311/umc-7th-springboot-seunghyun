package umc.spring.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.mission.domain.Mission;

@Getter
@Builder
public class RestaurantMissionResponseDTO {
    private Long missionId;
    private String content;
    private Integer score;
    private Long restaurantId;

    public static RestaurantMissionResponseDTO from(Mission mission) {
        return RestaurantMissionResponseDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .score(mission.getScore())
                .restaurantId(mission.getRestaurant().getId())
                .build();
    }
}
