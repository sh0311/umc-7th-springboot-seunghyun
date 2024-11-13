package umc.spring.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.mission.dto.MissionRequestDto;
import umc.spring.mission.dto.MissionResponseDto;
import umc.spring.mission.service.MissionService;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/mission")
    public ApiResponse<MissionResponseDto> createMission(@RequestBody @Valid MissionRequestDto request) {
        MissionResponseDto dto=missionService.createMission(request);
        return ApiResponse.onSuccess(dto);
    }
}
