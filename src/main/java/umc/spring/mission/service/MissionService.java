package umc.spring.mission.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.NotFoundHandler;
import umc.spring.mission.domain.Mission;
import umc.spring.mission.dto.MissionRequestDto;
import umc.spring.mission.dto.MissionResponseDto;
import umc.spring.mission.repository.MissionRepository;
import umc.spring.region.domain.Region;
import umc.spring.region.repository.RegionRepository;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.restaurant.repository.RestaurantRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Mission createMission(@Valid MissionRequestDto request) {
        Region region=regionRepository.findById(request.getRegionId()).orElseThrow(() -> new NotFoundHandler(ErrorStatus.REGION_NOT_FOUND));
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(() -> new NotFoundHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Mission mission=request.toEntity(restaurant);
        //양방향 연관관계 설정
        mission.mapRegion(region);

        missionRepository.save(mission);
        return mission;
    }
}
