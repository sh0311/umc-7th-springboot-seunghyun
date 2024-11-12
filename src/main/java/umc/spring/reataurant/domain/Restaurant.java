package umc.spring.reataurant.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.reataurant.domain.enums.FoodCategory;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="restaurant_id")
    private Long id;

    private String name;
    private String location;
    //별점
    private Float totalScore;


    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", totalScore=" + totalScore +
                ", foodCategory=" + foodCategory +// region의 이름 출력
                '}';
    }
}
