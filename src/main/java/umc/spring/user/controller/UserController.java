package umc.spring.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.mission.service.MissionService;
import umc.spring.review.service.ReviewService;
import umc.spring.user.dto.UserMissionListResponseDTO;
import umc.spring.user.dto.UserRequestDto;
import umc.spring.user.dto.UserResponseDto;
import umc.spring.user.dto.UserReviewResponseListDTO;
import umc.spring.user.service.UserService;
import umc.spring.validation.annotation.CheckPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final MissionService missionService;

    @PostMapping
    public ApiResponse<UserResponseDto> join(@RequestBody @Valid UserRequestDto request){
        UserResponseDto userDto = userService.join(request);
        return ApiResponse.onSuccess(userDto);
    }

    @Operation(summary="내가 쓴 리 조회 API", description = "내가 쓴 리뷰를 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "내 유저 아이디, path variable 입니다!")
    })
    @GetMapping("{userId}/reviews") //로그인 구현 후 수정하기 (@AuthenticationPrincipal)
    public ApiResponse<UserReviewResponseListDTO> getMyReviews(@PathVariable(name = "userId") Long userId,
                                                               @CheckPage
                                                               @PageableDefault(size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable){

        UserReviewResponseListDTO dtos=reviewService.getMyReviews(userId, pageable);
        return ApiResponse.onSuccess(dtos);
    }


    @Operation(summary="내 진행중인 미션 API", description = "내 진행중인 미션 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "내 유저 아이디, path variable 입니다!")
    })
    @GetMapping("/{userId}/missions")
    public ApiResponse<UserMissionListResponseDTO> getMyMissionsComplete(@PathVariable(name="userId") Long userId,
                                                                         @RequestParam(name="isComplete") boolean isComplete,
                                                                         @PageableDefault(size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable,
                                                                         @RequestParam Long missionId){

        UserMissionListResponseDTO dto=missionService.getMyMissionComplete(userId, isComplete, pageable, missionId);
        return ApiResponse.onSuccess(dto);
    }


}

