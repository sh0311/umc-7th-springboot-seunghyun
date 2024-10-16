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


    //연관관계 편의 메소드 ("미션은 한 번에 하나의 지역과만 연결"되고 + 미션과 지역은 처음에 한 번 매핑하고나서 바뀔 가능성이 적고, 명확하게 정의돼서 하나의 map으로 관리)
    public void mapRegion(Region region) {
        if(this.region!=null){ //기존에 매핑된 region이 있다면 연결 끊어주기
            region.getMissions().remove(this);
        }
        //새로운 region과 매핑해주기
        this.region = region;
        region.getMissions().add(this);
    }

}
