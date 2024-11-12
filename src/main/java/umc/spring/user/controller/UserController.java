package umc.spring.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.user.dto.UserRequestDto;
import umc.spring.user.dto.UserResponseDto;
import umc.spring.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponseDto> join(@RequestBody @Valid UserRequestDto request){
        UserResponseDto userDto = userService.join(request);
        return ApiResponse.onSuccess(userDto);
    }
}
