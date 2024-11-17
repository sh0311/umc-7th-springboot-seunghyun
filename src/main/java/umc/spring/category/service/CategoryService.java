package umc.spring.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public boolean isValid(List<Long> ids){
        return ids.stream()
                .allMatch(id -> categoryRepository.existsById(id));
    }
}
