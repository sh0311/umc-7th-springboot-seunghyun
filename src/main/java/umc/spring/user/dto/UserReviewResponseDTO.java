package umc.spring.user.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.review.domain.Review;

import java.time.LocalDate;

@Getter
@Builder
public class UserReviewResponseDTO {
    private String username;
    private Float score;
    private String body;
    private LocalDate createdAt;


    public static UserReviewResponseDTO from(Review review) {
        return UserReviewResponseDTO.builder()
                .username(review.getUser().getName())
                .score(review.getScore())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}
