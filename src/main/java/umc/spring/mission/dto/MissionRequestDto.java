package umc.spring.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.mission.domain.Mission;
import umc.spring.region.domain.Region;
import umc.spring.restaurant.domain.Restaurant;

@Getter
public class MissionRequestDto {
    @NotBlank
    private String content;
    @NotNull
    private Integer score;
    @NotNull
    private int confirmNum;
    @NotNull
    private Long regionId;
    @NotNull
    private Long restaurantId;


    public Mission toEntity(Restaurant restaurant) {
        return Mission.builder()
                .content(this.content)
                .score(this.score)
                .confirmNum(this.confirmNum)
                .restaurant(restaurant)
                .build();
    }

}
