package umc.spring.review.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.NotFoundHandler;
import umc.spring.mission.domain.Mission;
import umc.spring.mission.repository.MissionRepository;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.restaurant.dto.RestaurantMissionListResponseDTO;
import umc.spring.restaurant.dto.RestaurantMissionResponseDTO;
import umc.spring.restaurant.repository.RestaurantRepository;
import umc.spring.review.domain.Review;
import umc.spring.review.dto.ReviewRequestDto;
import umc.spring.review.dto.ReviewResponseDto;
import umc.spring.review.repository.ReviewRepository;
import umc.spring.user.domain.User;
import umc.spring.user.dto.UserReviewResponseListDTO;
import umc.spring.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public ReviewResponseDto createReview(@Valid ReviewRequestDto request, Long userId) {
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(() -> new NotFoundHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
        User user=userRepository.findById(userId).orElseThrow(() -> new NotFoundHandler(ErrorStatus.USER_NOT_FOUND));

        Review review=request.toEntity(restaurant, user);

        reviewRepository.save(review);

        return ReviewResponseDto.from(review);

    }


    public UserReviewResponseListDTO getMyReviews(Long userId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundHandler(ErrorStatus.USER_NOT_FOUND));

        int page=pageable.getPageNumber();
        int size= pageable.getPageSize();
        Sort sort=pageable.getSort();

        /*
        //No-Offset기반 페이징에서 page는 아예 쓰이지 않고 lastId를 기준으로 페이징된다.
        //하지만 PageRequest는 page를 꼭 인자로 가져야해서 page가 현재 의미 없는 값이라는 걸 나타내기 위해 0을 쓴다고 한다.
        Pageable pageRequest= PageRequest.of(0, size, sort);
        */

        Pageable pageRequest= PageRequest.of(page, size, sort);

        Page<Review> reviews=reviewRepository.findByUser_Id(userId, pageRequest);

        UserReviewResponseListDTO list = UserReviewResponseListDTO.from(reviews);

        return list;
    }

    public RestaurantMissionListResponseDTO findByRestaurant_Id(Long restaurantId, Pageable pageable) {

        int page=pageable.getPageNumber();
        int size= pageable.getPageSize();
        Sort sort=pageable.getSort();

        Pageable pageRequest= PageRequest.of(page, size, sort);

        Page<Mission> missions=missionRepository.findByRestaurant_Id(restaurantId, pageable);
        RestaurantMissionListResponseDTO dto = RestaurantMissionListResponseDTO.from(missions);
        return dto;
    }
}
