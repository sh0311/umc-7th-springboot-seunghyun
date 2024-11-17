package umc.spring.review.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.NotFoundHandler;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.restaurant.repository.RestaurantRepository;
import umc.spring.review.domain.Review;
import umc.spring.review.dto.ReviewRequestDto;
import umc.spring.review.dto.ReviewResponseDto;
import umc.spring.review.repository.ReviewRepository;
import umc.spring.user.domain.User;
import umc.spring.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public ReviewResponseDto createReview(@Valid ReviewRequestDto request, Long userId) {
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(() -> new NotFoundHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
        User user=userRepository.findById(userId).orElseThrow(() -> new NotFoundHandler(ErrorStatus.USER_NOT_FOUND));

        Review review=request.toEntity(restaurant, user);

        reviewRepository.save(review);

        return ReviewResponseDto.from(review);

    }


}
