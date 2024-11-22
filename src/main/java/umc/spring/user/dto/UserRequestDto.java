package umc.spring.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.user.domain.User;
import umc.spring.user.enums.Gender;
import umc.spring.user.enums.Role;
import umc.spring.user.enums.UserStatus;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;    // 이메일 필드 추가
    @NotBlank
    private String password;    // 비밀번호 필드 추가
    @NotNull
    private Integer gender;
    @Size(min = 5, max = 12)
    private String address;
    @ExistCategories
    private List<Long> preferCategory;
    @NotNull
    private Role role;    // 역할 필드 추가

    public User toEntity() {
        //gender는 enum이라 string->enum처리
        if (this.gender == null) {
            gender=1;
        }
        Gender genderEnum = null;
        switch (gender) {
            case 1: genderEnum = Gender.MALE; break;
            case 2: genderEnum = Gender.FEMALE; break;
        }
        return User.builder()
                .name(this.name)
                .gender(genderEnum)
                .address(this.address)
                .inActiveDate(null)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }


}
