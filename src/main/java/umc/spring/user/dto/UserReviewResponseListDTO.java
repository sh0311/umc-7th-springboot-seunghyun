package umc.spring.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import umc.spring.review.domain.Review;

import java.util.List;

@Getter
@Builder
public class UserReviewResponseListDTO {
    private List<UserReviewResponseDTO> reviews;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;

    public static UserReviewResponseListDTO from(Page<Review> reviews) {

        List<UserReviewResponseDTO> reviewList=reviews.getContent()
                .stream()
                .map(UserReviewResponseDTO::from)
                .toList();

        return UserReviewResponseListDTO.builder()
                .reviews(reviewList)
                .listSize(reviews.getSize())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
