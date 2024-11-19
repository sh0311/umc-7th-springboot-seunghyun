package umc.spring.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import umc.spring.mission.domain.Mission;

import java.util.List;

@Getter
@Builder
public class RestaurantMissionListResponseDTO {
    private List<RestaurantMissionResponseDTO> missionList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;

    public static RestaurantMissionListResponseDTO from(Page<Mission> missions) {
        List<RestaurantMissionResponseDTO> missionList=missions.getContent()
                .stream()
                .map(RestaurantMissionResponseDTO::from)
                .toList();

        return RestaurantMissionListResponseDTO.builder()
                .missionList(missionList)
                .listSize(missions.getSize())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
