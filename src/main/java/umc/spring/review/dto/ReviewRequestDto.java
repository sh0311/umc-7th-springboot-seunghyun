package umc.spring.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.restaurant.domain.Restaurant;
import umc.spring.review.domain.Review;
import umc.spring.user.domain.User;

@Getter
public class ReviewRequestDto {
    private String content;
    @NotNull
    private Integer score;


    public Review toEntity(Restaurant restaurant, User user){
        return Review.builder()
                .content(this.content)
                .score(this.score)
                .restaurant(restaurant)
                .user(user)
                .build();
    }
}
