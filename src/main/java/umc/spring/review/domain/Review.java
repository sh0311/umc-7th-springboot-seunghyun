package umc.spring.review.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.user.domain.User;
import umc.spring.reataurant.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    private Integer score;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="review", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> images=new ArrayList<>();
}
