package umc.spring.review.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.review.domain.Review;

import java.time.LocalDate;

@Getter
@Builder
public class ReviewPreViewDTO {
    private String ownerNickname;
    private Float score;
    private String body;
    private LocalDate createdAt;


    public static ReviewPreViewDTO from(Review review) {
        return ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getScore())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate()) //LocalDateTime->LocalDate
                .build();
    }
}
