package umc.spring.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.review.dto.ReviewRequestDto;
import umc.spring.review.dto.ReviewResponseDto;
import umc.spring.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/{userId}") //로그인 구현 후 수정
    public ApiResponse<ReviewResponseDto> createReview(@RequestBody @Valid ReviewRequestDto request, @PathVariable Long userId) {
        ReviewResponseDto response=reviewService.createReview(request, userId);
        return ApiResponse.onSuccess(response);
    }
}
