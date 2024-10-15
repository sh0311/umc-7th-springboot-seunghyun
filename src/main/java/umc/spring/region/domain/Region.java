package umc.spring.region.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private Long id;

    private String name;
    //미션 완료 개수
    private Integer completeNum;
    //해당 지역 누적 포인트(by 완료)
    private Integer score_sum;
}
