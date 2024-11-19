package umc.spring.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.review.service.ReviewService;
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

    @PostMapping
    public ApiResponse<UserResponseDto> join(@RequestBody @Valid UserRequestDto request){
        UserResponseDto userDto = userService.join(request);
        return ApiResponse.onSuccess(userDto);
    }


    @GetMapping("{userId}/reviews") //로그인 구현 후 수정하기 (@AuthenticationPrincipal)
    public ApiResponse<UserReviewResponseListDTO> getMyReviews(@PathVariable(name = "userId") Long userId,
                                                               @CheckPage
                                                               @PageableDefault(size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable){

        UserReviewResponseListDTO dtos=reviewService.getMyReviews(userId, pageable);
        return ApiResponse.onSuccess(dtos);
    }

}

