package umc.spring.region.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.mission.domain.Mission;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private Long id;

    private String name;
    //미션 완료 개수
    @Builder.Default
    private Integer completeNum=0;
    //해당 지역 누적 포인트(by 완료)
    private Integer scoreSum;

    @OneToMany(mappedBy = "region", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions=new ArrayList<>();
}
