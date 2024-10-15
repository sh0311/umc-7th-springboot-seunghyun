package umc.spring.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.global.BaseEntity;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_prefer_id")
    private Long id;

    @Builder.Default
    private boolean isComplete=false;

    //성공 요청 보냈는지
    @Builder.Default
    private boolean requestSuccess=false;
}
