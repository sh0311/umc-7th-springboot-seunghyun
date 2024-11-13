package umc.spring.restaurant.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;
import umc.spring.restaurant.domain.enums.FoodCategory;

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
    private FoodCategory foodCategory; // mysql에서 소문자로 입력해도 알아서 대문자로 바뀜

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
