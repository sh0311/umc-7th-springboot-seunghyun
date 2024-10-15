package umc.spring.alarm.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.alarm.domain.enums.AlarmType;
import umc.spring.global.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)gi
@Getter
@Builder
@AllArgsConstructor
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alarm_id")
    private Long id;

    private String title;
    private String description;
    private boolean is_confirmed;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;



}
