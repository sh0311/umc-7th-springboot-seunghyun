package umc.spring.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.global.BaseEntity;
import umc.spring.user.enums.Gender;
import umc.spring.user.enums.UserStatus;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    @Builder.Default
    private Integer score=0;
    private LocalDate inActiveDate;

    @NotNull
    private String email;
    private String phone;
}
