package umc.spring.mapping.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.mapping.service.UserMissionService;
import umc.spring.validation.annotation.IsChallenging;


@RestController
@RequiredArgsConstructor
@Validated //PathVariable에 어노테이션 붙이는 경우엔 Controller에 @Validated 넣어줘야함!!
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PatchMapping("/mission/{missionId}/{userId}") //로그인 구현 후 수정
    public ApiResponse<String> startChallenge(@PathVariable("missionId") @IsChallenging Long missionId, @PathVariable("userId") Long userId) {
        userMissionService.startChallenge(missionId, userId);
        return ApiResponse.onSuccess("도전중 미션에 추가되었습니다");
    }
}
