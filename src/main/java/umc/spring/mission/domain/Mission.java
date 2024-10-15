package umc.spring.mission.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.question.domain.Question;
import umc.spring.reataurant.domain.Restaurant;
import umc.spring.region.domain.Region;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mission_id")
    private Long id;

    private String content;
    private Integer score;
    private int confirmNum;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    private Region region;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;


    //연관관계 편의 메소드
    public void mapRegion(Region region) {
        this.region = region;
        region.getMissions().add(this);
    }

}
