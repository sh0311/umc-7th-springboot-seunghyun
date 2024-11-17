package umc.spring.review.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.review.domain.Review;

@Getter
@Builder
public class ReviewResponseDto {
    String username;
    String content;
    Long restaurantId;


    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .username(review.getUser().getName())
                .content(review.getContent())
                .restaurantId(review.getRestaurant().getId())
                .build();
    }
}
