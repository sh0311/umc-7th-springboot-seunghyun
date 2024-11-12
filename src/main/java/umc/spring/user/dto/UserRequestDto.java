package umc.spring.user.dto;

import lombok.Getter;
import umc.spring.user.domain.User;
import umc.spring.user.enums.Gender;
import umc.spring.user.enums.UserStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserRequestDto {
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String email;
    private String phone;
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
