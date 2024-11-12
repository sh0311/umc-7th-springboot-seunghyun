package umc.spring.user.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.user.domain.User;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDto {
    Long userId;
    LocalDateTime createdAt;

    //entity->dto
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
