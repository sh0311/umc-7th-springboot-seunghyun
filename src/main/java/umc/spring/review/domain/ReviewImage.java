package umc.spring.review.domain;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;

import java.awt.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;


    //연관관계 편의 메소드
    public void mapReview(Review review) {
        this.review=review;
        review.getImages().add(this);
    }
}
