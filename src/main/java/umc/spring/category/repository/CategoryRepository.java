package umc.spring.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.category.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
