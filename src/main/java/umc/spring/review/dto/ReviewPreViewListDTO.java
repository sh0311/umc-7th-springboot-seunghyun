package umc.spring.review.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import umc.spring.review.domain.Review;

import java.util.List;

@Getter
@Builder
public class ReviewPreViewListDTO {
    List<ReviewPreViewDTO> reviewList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;

    public static ReviewPreViewListDTO from(Page<Review> reviews) {
        //Page<Review> reviews의 각 리뷰들을 ReviewPreViewDTO로 변환
        List<ReviewPreViewDTO> reviewList = reviews.getContent() //Page객체에서 List<Review> 가져오려고
                .stream()
                .map(ReviewPreViewDTO::from)
                .toList();

        return ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviews.getSize())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();

    }
}
