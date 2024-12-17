package umc.spring.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.restaurant.dto.RestaurantMissionListResponseDTO;
import umc.spring.restaurant.dto.RestaurantMissionResponseDTO;
import umc.spring.restaurant.service.RestaurantServiceImpl;
import umc.spring.review.domain.Review;
import umc.spring.review.dto.ReviewPreViewListDTO;
import umc.spring.review.service.ReviewService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistRestaurant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;
    private final ReviewService reviewService;

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary="특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewPreViewListDTO> getReviewList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
                                                           @PageableDefault(size=10, sort="createdAt", direction= Sort.Direction.DESC) Pageable pageable) { //page를 global 설정에서 1부터 시작하게끔 해서 여기서 default 값 지정안해줘도됨
        ReviewPreViewListDTO reviewLists=restaurantService.getReviewList(restaurantId, pageable);
        return ApiResponse.onSuccess(reviewLists);
    }

    @GetMapping("/{restaurantId}/missions")
    @Operation(summary="특정 가게의 미션 목록 조회 API", description = "특정 가게의 리뷰들의 미션을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<RestaurantMissionListResponseDTO> getRestaurantMission(@PathVariable (name = "restaurantId") Long restaurantId,
                                                                              @PageableDefault(size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable){

        RestaurantMissionListResponseDTO dto = reviewService.findByRestaurant_Id(restaurantId, pageable);
        return ApiResponse.onSuccess(dto);
    }

    @PostMapping(
            value = "/{storeId}/reviews",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") @Valid StoreRequestDTO.ReviewDTO request,

            @ExistStore @PathVariable(name = "restaurantId") Long restaurantId,
            @ExistUser @RequestParam(name = "userId") Long userId,
            @RequestPart("reviewPicture") MultipartFile reviewPicture
    ) {
        Review review = storeCommandService.createReview(userId, restaurantId, request, reviewPicture);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

}


