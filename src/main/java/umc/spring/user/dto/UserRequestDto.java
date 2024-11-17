package umc.spring.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.user.domain.User;
import umc.spring.user.enums.Gender;
import umc.spring.user.enums.UserStatus;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @Size(min=5, max=12)
    private String address;
    private String email;
    private String phone;
    @ExistCategories
    private List<Long> preferCategory;

    public User toEntity() {
        //gender는 enumd이라 string->enum처리
        Gender genderEnum = null;
        switch (gender) {
            case "male":
                genderEnum = Gender.MALE;
                break;
            case "female":
                genderEnum = Gender.FEMALE;
        }
        return User.builder()
                .name(this.name)
                .gender(genderEnum)
                .birth(this.birthDate)
                .address(this.address)
                .inActiveDate(null)
                .email(this.email)
                .phone(this.phone)
                .build();
    }


}
