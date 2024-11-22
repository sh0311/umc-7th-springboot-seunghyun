package umc.spring.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.NotFoundHandler;
import umc.spring.category.domain.Category;
import umc.spring.category.repository.CategoryRepository;
import umc.spring.mapping.domain.UserPrefer;
import umc.spring.mapping.service.UserPreferService;
import umc.spring.user.domain.User;
import umc.spring.user.dto.UserRequestDto;
import umc.spring.user.dto.UserResponseDto;
import umc.spring.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserPreferService userPreferService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto join(UserRequestDto request) {

        User user=request.toEntity();
        System.out.println(user.getPassword());
        //비밀번호 암호화
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); //user를 저장해야 UserPrefer에 user객체와 연관관계 매핑 가능

        //foodCategoryList 생성
        List<Category> foodCategoryList=request.getPreferCategory().stream()
                .map(category->{return categoryRepository.findById(category).orElseThrow(()->new NotFoundHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();

        //request로 받은 foodCategoryList로 userPreferList 작성
        List<UserPrefer> userPreferList= userPreferService.toUserPrefer(foodCategoryList,user);
        return UserResponseDto.from(user);
    }
}
