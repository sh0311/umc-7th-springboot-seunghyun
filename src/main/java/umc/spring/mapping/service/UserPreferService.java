package umc.spring.mapping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.category.domain.Category;
import umc.spring.mapping.domain.UserPrefer;
import umc.spring.mapping.repository.UserPreferRepository;
import umc.spring.user.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPreferService {
    private final UserPreferRepository userPreferRepository;

    public List<UserPrefer> toUserPrefer(List<Category> foodCategoryList, User user){
        return foodCategoryList.stream()
                .map(foodCategory -> UserPrefer.builder()
                        .category(foodCategory)
                        .user(user)
                        .build())
                .toList();
    }
}
