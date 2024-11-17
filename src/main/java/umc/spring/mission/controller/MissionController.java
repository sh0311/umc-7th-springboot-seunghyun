package umc.spring.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.mapping.service.UserMissionService;
import umc.spring.mission.domain.Mission;
import umc.spring.mission.dto.MissionRequestDto;
import umc.spring.mission.dto.MissionResponseDto;
import umc.spring.mission.service.MissionService;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;
    private final UserMissionService userMissionService;

    @PostMapping("/mission")
    public ApiResponse<MissionResponseDto> createMission(@RequestBody @Valid MissionRequestDto request) {
        //가게에 미션 추가하기
        Mission mission=missionService.createMission(request);
        //가게의 미션을 도전중인 미션에 추가하기
        userMissionService.createUserMission(mission);

        return ApiResponse.onSuccess(MissionResponseDto.from(mission));
    }

}
