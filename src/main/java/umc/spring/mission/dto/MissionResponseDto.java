package umc.spring.mission.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.mission.domain.Mission;

@Getter
@Builder
public class MissionResponseDto {
    private Long missionId;
    private String content;
    private Long regionId;
    private Long restaurantId;


    public static MissionResponseDto from(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .regionId(mission.getRegion().getId())
                .restaurantId(mission.getRestaurant().getId())
                .build();
    }

}
