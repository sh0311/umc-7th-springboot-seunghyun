package umc.spring.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import umc.spring.global.BaseEntity;
import umc.spring.user.domain.enums.Gender;
import umc.spring.user.domain.enums.UserStatus;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
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

    private Integer score;
    private LocalDate inActiveDate;

    @NotNull
    private String email;
    private String phone;
}
