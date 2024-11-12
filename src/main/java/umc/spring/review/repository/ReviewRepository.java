package umc.spring.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
